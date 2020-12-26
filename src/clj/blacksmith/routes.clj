(ns blacksmith.routes
  (:require [blacksmith.character :as character]
            [blacksmith.response :as response]
            [compojure.core :refer [context defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.defaults :as defaults]))

(defn index-handler
  "Return index.html content for requests to the root uri"
  [req]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (slurp "resources/public/index.html")})

(defroutes app-routes
  (context "/v1" []
           (GET "/character/:id" [id] (response/one (character/get-one id)))
           (GET "/character" [] (response/paged (character/get-all))))
  (GET "/app/:view{.*}" req (index-handler req))
  (route/resources "/"))

(defn api-handler
  [handler]
  (-> handler
      (defaults/wrap-defaults defaults/api-defaults)
      wrap-json-response
      wrap-json-body))

(defn site-handler
  [handler]
  (-> handler
      (defaults/wrap-defaults defaults/site-defaults)))

(def handler
  (site-handler (api-handler app-routes)))
