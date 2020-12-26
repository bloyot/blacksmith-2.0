(ns blacksmith.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 ::view
 (fn [db _]
   (:view db)))
