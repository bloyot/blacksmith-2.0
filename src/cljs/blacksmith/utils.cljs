(ns blacksmith.utils
  (:require [ajax.core :as ajax]
            [cemerick.url :as url]))

(defn request
  ([method path]
   (request method path nil))
  ([method path params]
   {:method method
    :uri path
    :params params
    :format (ajax/json-request-format)
    :response-format (ajax/json-response-format {:keywords? true})}))
