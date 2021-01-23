(ns blacksmith.server
  (:require
   [blacksmith.config :as conf]
   [blacksmith.db.migration :as migration]
   [blacksmith.routes :as routes]
   [hugsql.core :as hugsql]
   [hugsql.adapter.next-jdbc :as next-adapter]
   [mount.core :as mount]
   [next.jdbc :as jdbc]
   [next.jdbc.result-set :refer [as-maps-adapter
                                 as-unqualified-kebab-maps
                                 clob-column-reader]]
   [ring.adapter.jetty :as jetty]
   [ring.middleware.reload :refer [wrap-reload]])
  (:import (org.eclipse.jetty.server Server)))

(def app
  (if (= (conf/env) :local)
    (wrap-reload routes/handler {:dirs ["src/clj"]})
    routes/handler))

(mount/defstate ^{:on-reload :noop} ApplicationServer
  :start
  (jetty/run-jetty #'app {:port 3000 :join? false})
  :stop (when instance? Server ApplicationServer
              (.stop ApplicationServer)))

(def database-opts
  (assoc jdbc/unqualified-snake-kebab-opts
          :builder-fn
          (as-maps-adapter as-unqualified-kebab-maps clob-column-reader)))

(defn -main
  [& args]
  (println (str "Starting with env:" (conf/env)))
  (hugsql/set-adapter!
   (next-adapter/hugsql-adapter-next-jdbc database-opts))
  (mount/start)
  (println "Running db migrations...")
  (migration/migrate))
