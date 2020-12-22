(ns blacksmith.response
  (:require [ring.util.response :as ring]))

(defn ok
  [body]
  (-> body
      ring/response
      (ring/content-type "application/json")))

(defn not-found []
  (-> (str "{\"message\":\"not-found\"}")
      ring/not-found
      (ring/content-type "application/json")))

(defn one
  [content]
  (if one
    (ok content)
    (not-found)))

(defn paged
  [content]
  (ok {:content content
       ;; TODO don't hard code
       :page {:size -1
              :total_elements -1
              :total_pages -1
              :current_page 1}}))
