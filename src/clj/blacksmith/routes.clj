(ns blacksmith.routes
  (:require [compojure.core :refer [defroutes GET]]
            [ring.middleware.defaults :as defaults]))

(defroutes routes
  (GET "/" [] "hellow world"))

(defn api-handler
  [handler]
  (defaults/wrap-defaults handler defaults/api-defaults))

(defn site-handler
  [handler]
  (defaults/wrap-defaults handler defaults/site-defaults))

(def handler
  (site-handler (api-handler routes)))
