(ns blacksmith.events
  (:require [re-frame.core :as rf]
            [day8.re-frame.http-fx]))

(rf/reg-event-fx
 ::view
 (fn [{db :db} [_ view route-params]]
   {:db (assoc db :view {:name view
                         :route-params route-params})}))

(rf/reg-event-fx
 ::fetch
 (fn [{db :db} [_ entity request]]
   {:db (assoc-in db [entity :status] :in-flight)
    :http-xhrio (assoc request
                       :on-success [::success entity]
                       :on-failure [::failure entity])}))

(rf/reg-event-fx
 ::success
 (fn [{db :db} [_ entity resp]]
   {:db (-> db
            (assoc-in [entity :status] :complete)
            (assoc-in [entity :data] resp))}))

(rf/reg-event-fx
 ::failure
 (fn [{db :db} [_ entity resp]]
   {:db (-> db
            (assoc-in [entity :status] :error)
            (update entity dissoc :data)
            (assoc-in [entity :error] resp))}))
