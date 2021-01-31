(ns blacksmith.components.character.details
  (:require [blacksmith.formatters :as formatters]
            [blacksmith.components.ui :as ui]
            [blacksmith.components.text :as text]
            [reagent-material-ui.core.box :refer [box]]
            [reagent-material-ui.core.grid :refer [grid]]
            [reagent-material-ui.core.typography :refer [typography]]))

(defn detail
  [title value]
  [:span {:class "flex justify-center space-x-2"}
   [typography {:variant "subtitle2"} title]
   [box {:border 1 :borderColor "grey.500"}
    [:div {:class "mx-2"}
     [typography {:variant "body1"} value]]]])

(defn detail-panel
  [character]
  [grid {:container true :spacing 3}
   [grid {:item true :xs 3}
    [detail "Proficiency Bonus"
     (formatters/modifier (:proficiency-bonus character))]]
   [grid {:item true :xs 3}
    [detail "Armor Class" (:armor-class character)]]
   [grid {:item true :xs 3}
    [detail "Initiative" (formatters/modifier (:initiative character))]]
   [grid {:item true :xs 3}
    [detail "Speed" (str (:speed character) "ft")]]
   [grid {:item true :xs 3}
    [detail "Max Hit Points" (:hit-point-max character)]]
   [grid {:item true :xs 3}
    [detail "Experience" (:experience character)]]
   [grid {:item true :xs 3}
    [detail "Perception" "13"]]
   [grid {:item true :xs 3}]
   [grid {:item true :xs 2}]
   [grid {:item true :xs 4}
    [:div {:class "flex-col"}
      [typography {:variant "subtitle2"} "Proficiencies"]
      [typography {:variant "body2"}
       (formatters/misc-proficiencies character)]]]
   [grid {:item true :xs 4}
    [:div {:class "flex-col"}
      [typography {:variant "subtitle2"} "Languages"]
      [typography {:variant "body2"}
       (formatters/languages character)]]]
   [grid {:item true :xs 2}]])

(defn panel
  [character]
  [:div
   [ui/accordion
    [text/title-5 "Info"] [detail-panel character]]
   [ui/accordion
    [text/title-5 "Attacks"] "todo"]
   [ui/accordion
    [text/title-5 "Race"] "todo"]
   [ui/accordion
    [text/title-5 "Background"] "todo"]])
