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

(defn execute!
  "Takes a query vector (query string + params) and executes it, returning
  all values as a result set."
  [query-v]
  (jdbc/execute! datasource query-v jdbc/unqualified-snake-kebab-opts))

(defn execute-one!
  "Takes a query vector (query string + params) and executes it, returning
  a single value (if present) as a result set."
  [query-v]
  (first (jdbc/execute! datasource query-v jdbc/unqualified-snake-kebab-opts)))
