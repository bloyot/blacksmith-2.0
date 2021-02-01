(ns blacksmith.components.character.spells
  (:require [blacksmith.components.text :as text]
            [blacksmith.character-utils :as cutils]
            [blacksmith.formatters :as formatters]
            [blacksmith.utils.spell-slots :refer [spell-slots]]
            [blacksmith.macros :refer [seq-with-keys]]
            [reagent-material-ui.core.box :refer [box]]
            [reagent-material-ui.core.table :refer [table]]
            [reagent-material-ui.core.table-body :refer [table-body]]
            [reagent-material-ui.core.table-cell :refer [table-cell]]
            [reagent-material-ui.core.table-container :refer [table-container]]
            [reagent-material-ui.core.table-head :refer [table-head]]
            [reagent-material-ui.core.table-row :refer [table-row]]))

(defn header
  [spell-level slots]
  [table-cell {:align "center"}
   (if (not= "-" (nth slots (- spell-level 1)))
     [text/body spell-level]
     [text/secondary spell-level])])

;; need to rework this a little to account for third dimension of spells?
(defn cell
  [content]
  [table-cell {:align "center"} [text/body content]])

;; TODO handle warlock stuff separately
;; TODO 1/3 caster table for arcane trickster and eldritch knight
(defn spell-table
  [character class sub-class]
  (let [level (cutils/char-class->level character class)
        caster-type (cutils/caster-type class sub-class)
        slots (as-> (caster-type spell-slots) ss
                (nth ss (- level 1))
                (partition 9 0 (repeat 9 "-") ss)
                (flatten ss))]
    [:div {:class "m-4"}
     [:div {:class "pb-2 flex justify-center"}
      [text/subtitle-1-secondary "Spells Known"]]
     [box {:border 1 :borderRadius 3 :borderColor "grey.500"}
      [table-container
       [table {:size "small"}
        [table-head
         [table-row
          ;; why cons works here and not other ways of making a list????
          (cons
           ^{:key -2} [header "Level"]
           (cons
            ^{:key -1} [cell "Cantrips"]
            (seq-with-keys (range 1 10) #(header % slots))))]]
        [table-body
         [table-row
          (cons
           ^{:key -2} [cell "Spell Slots"]
           (cons
            ^{:key -1} [cell 0]
            (seq-with-keys slots #(cell %))))]]]]]]))

(defn spell-info
  [character class sub-class]
  (let [attribute (cutils/class->spell-attribute class)
        attribute-name (attribute formatters/ability-score-names)
        attribute-mod (cutils/as->modifier (get-in character [:ability-scores
                                                      attribute
                                                      :modified]))
        dc (+ 8 (:proficiency-bonus character) attribute-mod)
        attack-mod (formatters/modifier
                    (+ (:proficiency-bonus character) attribute-mod))]
    [:div {:class "px-4"}
      [text/body (str "Primary spellcasting attribute: " attribute-name)]
      [text/body (str "Spell save DC: " dc)]
      [text/body (str "Spell attack modifier: " attack-mod)]]))

(defn spells-known
  "TODO"
  [spells]
  [:div "TODO"])

(defn panel
  [character class sub-class]
  [:div
   [text/subtitle-2 "Spellcasting"]
   [spell-info character class sub-class]
   [spell-table character class sub-class]
   (when-let [spells (:spells-known character)]
     [spells-known spells])])
