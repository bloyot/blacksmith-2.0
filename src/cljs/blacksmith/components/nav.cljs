(ns blacksmith.components.nav
  (:require [reagent-material-ui.core.app-bar :refer [app-bar]]
            [reagent-material-ui.core.button :refer [button]]
            [reagent-material-ui.core.icon-button :refer [icon-button]]
            [reagent-material-ui.icons.menu :refer [menu]]
            [reagent-material-ui.core.toolbar :refer [toolbar]]
            [reagent-material-ui.core.typography :refer [typography]]))

(defn nav
  []
  [app-bar {:position "static"}
   [toolbar
    [:div {:class "flex justify-between w-full mx-8"}
     [:div {:class "flex items-center"}
      [icon-button {:edge "start"
                    :color "inherit"}
       [menu]]
      [:img {:class "block h-8 w-auto"
             :src "/img/blacksmith-logo.png"
             :alt "Blacksmith"}]]
     [:span {:class "flex items-center"}
      [button {:color "inherit"} "Login"]
      [button {:color "inherit"} "Sign up"]]]]])
