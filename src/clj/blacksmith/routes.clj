(ns blacksmith.routes
  (:require [blacksmith.character :as character]
            [blacksmith.response :as response]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.defaults :as defaults]))

(defroutes app-routes
  (GET "/character/:id" [id] (response/one (character/get-one id)))
  (GET "/character" [] (response/paged (character/get-all)))
  (route/resources "/"))

;; TODO this is maybe not actually the way to do this???
;; Might cause problems with client side routing stuff
(defn wrap-root
  "Return index.html content for requests to the root uri"
  [handler]
  (fn [req]
    (println (:uri req))
    (handler (update-in req [:uri]
                      #(if (= "/" %) "/index.html" %)))))

(defn api-handler
  [handler]
  (-> handler
      (defaults/wrap-defaults defaults/api-defaults)
      wrap-json-response
      wrap-json-body))

(defn site-handler
  [handler]
  (-> handler
      (defaults/wrap-defaults defaults/site-defaults)
      wrap-root))

(def handler
  (site-handler (api-handler app-routes)))
