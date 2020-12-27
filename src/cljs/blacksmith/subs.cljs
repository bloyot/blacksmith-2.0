(ns blacksmith.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::view
 (fn [db _]
   (:view db)))

(rf/reg-sub
 ::character
 (fn [db [_ id]]
   (println "in sub " (first (filter #(= id (:id %)) (get-in db [:characters :data :content]))))
   (or (get-in db [:character :data])
       (first (filter #(= id (:id %)) (get-in db [:characters :data :content]))))))

(rf/reg-sub
 ::fetch
 (fn [db [_ entity]]
   (get-in db [entity :data])))

(rf/reg-sub
 ::fetch-status
 (fn [db [_ entity]]
   (get-in db [entity :status])))
