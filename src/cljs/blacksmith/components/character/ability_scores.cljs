(ns blacksmith.components.character.ability-scores
  (:require [blacksmith.character-utils :as cutils]
            [blacksmith.formatters :as formatters]
            [clojure.string :as str]
            [reagent-material-ui.core.table :refer [table]]
            [reagent-material-ui.core.table-body :refer [table-body]]
            [reagent-material-ui.core.table-cell :refer [table-cell]]
            [reagent-material-ui.core.table-container :refer [table-container]]
            [reagent-material-ui.core.table-head :refer [table-head]]
            [reagent-material-ui.core.table-row :refer [table-row]]
            [reagent-material-ui.core.tooltip :refer [tooltip]]
            [reagent-material-ui.core.typography :refer [typography]]))

(defn as-value
  [[_ as-value]]
  [tooltip {:title "test" :placement "right"}
   [:span {:class "flex justify-center space-x-1"}
    [typography {:variant "body1"} as-value]
    [typography {:variant "body1" :color "textSecondary"}
     (str "(" (formatters/modifier (cutils/as->modifier as-value)) ")")]]])

(defn ability-score->short-form
  "Takes a vector with ability score key and map and returns the
  shortened form, which is just a map of ability-score->modified
  value"
  [[ability-score-kw ability-score-map]]
  {ability-score-kw (:modified ability-score-map)})

(defn panel
  [character]
  (def -c character)
  [:div {:class "bg-white"}
   (let [bas (->> character
                 :ability-scores
                 (map ability-score->short-form)
                 (apply merge))]
     [table-container
      [table {:size "small"}
       [table-head
        [table-row
         (for [as bas]
           ^{:key (first as)} [table-cell {:align "center"}
                               (str/upper-case (name (first as)))])]]
       [table-body
        [table-row
         (for [as bas]
           ^{:key (first as)} [table-cell {:align "center"} [as-value as]])]]]])])
