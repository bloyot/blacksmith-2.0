(ns blacksmith.app
  (:require
   [blacksmith.components.nav :as nav]
   [blacksmith.views.character :as character]
   [blacksmith.views.characters :as characters]
   [blacksmith.utils :as utils]
   [blacksmith.events :as events]
   [blacksmith.subs :as subs]
   [reagent-material-ui.core.container :refer [container]]
   [re-frame.core :as rf]))

(def views {:characters characters/view
            :character character/view})

(defn app
  "Create the app shell and do initialization"
  []
  [:div {:class "bg-gray-200 min-h-screen"}
   [nav/nav]
   ;; background
   [container 
    ;; content
    [:div {:class "shadow-md bg-gray-100 min-h-screen mt-2"}
     (if-let [view @(rf/subscribe [::subs/view])]
       [(get views (:name view)) (:route-params view)]
       [:div "initializing"])]]])
