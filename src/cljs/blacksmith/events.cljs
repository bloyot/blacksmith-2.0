(ns blacksmith.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 ::view
 (fn [db [_ view route-params]]
   (assoc db :view {:name view
                    :route-params route-params})))
