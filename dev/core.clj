(ns dev.core
  (:require [blacksmith.server :refer [-main]]
            [blacksmith.db.migration :as migration]
            [mount.core :as mount]))

(mount/defstate h2-server
  :start (.start (org.h2.tools.Server/createTcpServer (into-array String [])))
  :stop (.stop h2-server))

(mount/defstate h2-console
  :start (.start (org.h2.tools.Server/createWebServer (into-array String [])))
  :stop (.stop h2-console))

(defn start
  []
  ;; mainly importing this to force compiliation of everything from this
  ;; namespace
  (-main)
  ;; run db fixtures
  (println "Migrating fixture data...")
  (migration/migrate-fixtures))

(defn stop
  []
  (mount/stop))
