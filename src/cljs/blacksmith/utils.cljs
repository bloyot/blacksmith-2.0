(ns blacksmith.utils
  (:require [ajax.core :as ajax]
            [cemerick.url :as url])
  (:refer-clojure :exclude [hash]))

(defn request
  ([method path]
   (request method path nil))
  ([method path params]
   {:method method
    :uri path
    :params params
    :format (ajax/json-request-format)
    :response-format (ajax/json-response-format {:keywords? true})}))

(defn hash
  []
  (:anchor (url/url (-> js/window .-location .-href))))

(defn set-hash
  [anchor]
  (set! (.-hash js/window.location) anchor))
  
