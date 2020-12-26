(ns blacksmith.app
  (:require
   [blacksmith.components.nav :as nav]
   [blacksmith.views.characters :as characters]
   [blacksmith.subs :as subs]
   [re-frame.core :as rf]))

(def view-fns {:characters characters/view})

(defn app
  "Create the app shell and do initialization"
  []
  [:div
   [nav/nav]
   ;; background
   [:div {:class "px-64 bg-gray-200 h-screen w-screen"}
    ;; content
    [:div {:class "shadow-md bg-gray-100 h-full w-full"}
     (when-let [view @(rf/subscribe [::subs/view])]
       [(get view-fns (:name view)) (:route-params view)])]]])
