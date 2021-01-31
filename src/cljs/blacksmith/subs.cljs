(ns blacksmith.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::view
 (fn [db _]
   (:view db)))

(rf/reg-sub
 ::character
 (fn [db [_ id]]
   (or (get-in db [:character id :data])
       (first (filter #(= id (str (:id %))) (get-in db [:characters :data :content]))))))

(rf/reg-sub
 ::fetch
 (fn [db [_ & entity-v]]
   (get-in db (flatten [entity-v :data]))))

(rf/reg-sub
 ::fetch-status
 (fn [db [_ & entity-v]]
   (get-in db (flatten [entity-v :status]))))

(rf/reg-sub
 ::character-tab
 (fn [db [_]]
   (get-in db [:character :tab])))
