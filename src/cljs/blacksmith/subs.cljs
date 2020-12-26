(ns blacksmith.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::view
 (fn [db _]
   (:view db)))

(rf/reg-sub
 ::fetch
 (fn [db [_ entity]]
   (get-in db [entity :data])))

(rf/reg-sub
 ::fetch-status
 (fn [db [_ entity]]
   (get-in db [entity :status])))
