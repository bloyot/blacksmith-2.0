(ns blacksmith.server
  (:require
   [blacksmith.config :as conf]
   [blacksmith.migration :as migration]
   [blacksmith.routes :as routes]
   [mount.core :as mount]
   [ring.adapter.jetty :as jetty]
   [ring.middleware.reload :refer [wrap-reload]])
  (:import (org.eclipse.jetty.server Server)))

(def app
  (if (= (conf/env) :local)
    (wrap-reload routes/handler {:dirs ["src/clj"]})
    routes/handler))

(mount/defstate ^{:on-reload :noop} ApplicationServer
  :start
  (jetty/run-jetty app {:port 3000 :join? false})
  :stop (when instance? Server ApplicationServer
              (.stop ApplicationServer)))

(defn -main
  [& args]
  (println (str "Starting with env:" (conf/env)))
  (mount/start)
  (println "Running db migrations...")
  (migration/migrate))
