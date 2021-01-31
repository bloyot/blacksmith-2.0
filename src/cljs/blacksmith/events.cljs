(ns blacksmith.events
  (:require [blacksmith.utils :as utils]
            [day8.re-frame.http-fx]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::view
 (fn [{db :db} [_ view route-params]]
   {:db (assoc db :view {:name view
                         :route-params route-params})}))

(rf/reg-event-fx
 ::fetch
 (fn [{db :db} [_ request & entity]]
   (let [entity-v (vec entity)]
     {:db (assoc-in db (flatten [entity-v :status]) :in-flight)
      :http-xhrio (assoc request
                         :on-success [::success entity-v]
                         :on-failure [::failure entity-v])})))

(rf/reg-event-fx
 ::success
 (fn [{db :db} [_ entity-v resp]]
   {:db (-> db
            (assoc-in (flatten [entity-v :status]) :complete)
            (assoc-in (flatten [entity-v :data]) resp))}))

(rf/reg-event-fx
 ::failure
 (fn [{db :db} [_ entity-v resp]]
   {:db (-> db
            (assoc-in (flatten [entity-v :status]) :error)
            (update-in entity-v dissoc :data)
            (assoc-in (flatten [entity-v :error]) resp))}))

(rf/reg-event-fx
 ::character-tab
 (fn [{db :db} [_ tab]]
   (utils/set-hash tab)
   {:db (assoc-in db [:character :tab] tab)}))
