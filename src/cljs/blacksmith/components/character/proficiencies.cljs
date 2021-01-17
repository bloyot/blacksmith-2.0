(ns blacksmith.components.character.proficiencies
  (:require [blacksmith.character-utils :as cutils :refer [saves skills]]
            [blacksmith.formatters :as formatters]
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
  [character profs show-stat?]
  (for [[prof stat] profs]
    (let [prof? (cutils/proficient? character prof)
          prof-mod (cutils/char->prof-modifier character prof)]
      ^{:key prof} [item prof prof? stat show-stat? prof-mod])))

(defn panel
  [character]
  [grid {:container true :spacing 1}
   [grid {:item true :xs 12}
    [:div {:class "flex justify-center pb-2"}
     [typography {:variant "h6"} "Saving Throws"]]]
   ;; saves
   (proficiency-set character saves false)
   ;; skills
   [grid {:item true :xs 12}
    [:div {:class "flex justify-center pb-2"}
     [typography {:variant "h6"} "Skills"]]]
   (proficiency-set character skills true)])

