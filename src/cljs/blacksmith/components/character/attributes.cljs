(ns blacksmith.components.character.attributes
  (:require [blacksmith.character-utils :as cutils]
            [blacksmith.formatters :as formatters]
            [clojure.string :as str]
            [reagent-material-ui.core.table :refer [table]]
            [reagent-material-ui.core.table-body :refer [table-body]]
            [reagent-material-ui.core.table-cell :refer [table-cell]]
            [reagent-material-ui.core.table-container :refer [table-container]]
            [reagent-material-ui.core.table-head :refer [table-head]]
            [reagent-material-ui.core.table-row :refer [table-row]]
            [reagent-material-ui.core.typography :refer [typography]]))

(defn as-value
  [[_ as-value]]
  [:span {:class "flex justify-center space-x-1"}
   [typography {:variant "body1"} as-value]
   [typography {:variant "body1" :color "textSecondary"}
    (str "(" (formatters/modifier (cutils/as->modifier as-value)) ")")]])

(defn panel
  [character]
  [:div {:class "bg-white"}
   (let [bas (:base-ability-scores character)]
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
