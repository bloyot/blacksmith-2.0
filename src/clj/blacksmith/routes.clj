(ns blacksmith.routes
  (:require [blacksmith.character :as character]
            [blacksmith.response :as response]
            [compojure.core :refer [defroutes GET]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.defaults :as defaults]))

(defroutes routes
  (GET "/character/:id" [id] (response/one (character/get-one id)))
  (GET "/character" [] (response/paged (character/get-all))))

(defn wrap-ignore-favicon-request
  [handler]
  (fn [request]
    (if (= (:uri request) "/favicon.ico")
      {:status 404}
      (handler request))))

(defn api-handler
  [handler]
  (-> handler
      (defaults/wrap-defaults defaults/api-defaults)
      wrap-json-response
      wrap-json-body
      wrap-ignore-favicon-request))

(defn site-handler
  [handler]
  (defaults/wrap-defaults handler defaults/site-defaults))

(def handler
  (site-handler (api-handler routes)))
