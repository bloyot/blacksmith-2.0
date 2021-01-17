(ns blacksmith.components.character.proficiencies
  (:require [blacksmith.character-utils :as cutils :refer [saves skills]]
            [blacksmith.formatters :as formatters]
            [blacksmith.components.ui :as ui]
            [reagent-material-ui.core.grid :refer [grid]]
            [reagent-material-ui.core.radio :refer [radio]]
            [reagent-material-ui.core.typography :refer [typography]]))

(defn item
  [name prof? stat show-stat? modifier]
  (let [display-name (formatters/proficiency-name name)
        display-stat (formatters/stat stat)]
    [grid {:item true :xs 2}
     [:div {:class "flex space-x-1"}
      [typography {:variant "subtitle2"}
       display-name]
      (when show-stat?
        [typography {:variant "subtitle2" :color "textSecondary"} (str "(" display-stat ")")])]
     [:div {:class "flex justify-start items-center space-x-1"}
      [radio {:checked prof?}]
      [typography {:variant "body1"} (formatters/modifier modifier)]]]))

(defn proficiency-set
  [character profs show-stat?])

(defn proficiency-panel
  [character profs show-stats?]
  [grid {:container true :spacing 1}
   (for [[prof stat] profs]
    (let [prof? (cutils/proficient? character prof)
          prof-mod (cutils/char->prof-modifier character prof)]
      ^{:key prof} [item prof prof? stat show-stats? prof-mod]))])

(defn panel
  [character]
  [:div
   [ui/accordion
    "Saving Throws" [proficiency-panel character saves false]]
   [ui/accordion
    "Skills" [proficiency-panel character skills true]]])

