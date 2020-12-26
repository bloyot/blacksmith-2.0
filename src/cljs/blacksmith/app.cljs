(ns blacksmith.app
  (:require
   [blacksmith.components.nav :as nav]
   [blacksmith.views.characters :as characters]
   [blacksmith.utils :as utils]
   [blacksmith.events :as events]
   [blacksmith.subs :as subs]
   [re-frame.core :as rf]))

(def views {:characters characters/view})

(defn active-view
  []
  )

(defn app
  "Create the app shell and do initialization"
  []
  [:div
   [nav/nav]
   ;; background
   [:div {:class "px-64 bg-gray-200 h-screen w-screen"}
    ;; content
    [:div {:class "shadow-md bg-gray-100 h-full w-full"}
     (if-let [view @(rf/subscribe [::subs/view])]
       [(get views (:name view) (:route-params view))]
       [:div "initializing"])]]])
