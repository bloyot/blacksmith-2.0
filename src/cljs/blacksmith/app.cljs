(ns blacksmith.app
  (:require
   [blacksmith.components.nav :as nav]
   [blacksmith.views.character :as character]
   [blacksmith.views.characters :as characters]
   [blacksmith.utils :as utils]
   [blacksmith.events :as events]
   [blacksmith.subs :as subs]
   [re-frame.core :as rf]))

(def views {:characters characters/view
            :character character/view})

(defn app
  "Create the app shell and do initialization"
  []
  [:div
   [nav/nav]
   ;; background
   [:div {:class "flex justify-center bg-gray-200 h-screen w-screen"}
    ;; content
    [:div {:class "shadow-md bg-gray-100 h-full w-3/5"}
     (if-let [view @(rf/subscribe [::subs/view])]
       [(get views (:name view)) (:route-params view)]
       [:div "initializing"])]]])
