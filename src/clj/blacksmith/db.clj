(ns blacksmith.db
  (:require [blacksmith.config :as conf]
            [mount.core :as mount]
            [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection])
  (:import (com.mchange.v2.c3p0 ComboPooledDataSource)))

(mount/defstate ^{:on-reload :noop} datasource
  :start
  ^PooledDataSource (connection/->pool ComboPooledDataSource (conf/get :db-spec))
  :stop (when (instance? ComboPooledDataSource datasource)
          (.close datasource)))

