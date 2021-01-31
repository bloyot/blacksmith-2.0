(ns blacksmith.components.character.ability-scores
  (:require [blacksmith.character-utils :as cutils]
            [blacksmith.components.text :as text]
            [blacksmith.formatters :as formatters]
            [blacksmith.macros :refer [seq-with-keys]]
            [clojure.string :as str]
            [reagent.core :as r]
            [reagent-material-ui.core.table :refer [table]]
            [reagent-material-ui.core.table-body :refer [table-body]]
            [reagent-material-ui.core.table-cell :refer [table-cell]]
            [reagent-material-ui.core.table-container :refer [table-container]]
            [reagent-material-ui.core.table-head :refer [table-head]]
            [reagent-material-ui.core.table-row :refer [table-row]]
            [reagent-material-ui.core.tooltip :refer [tooltip]]))

(defn as-tooltip
  [base modifiers]
  [:<>
   (seq-with-keys modifiers #(identity [text/body (:modifier %)]))
   #_(for [idx (range (count modifiers))
         :let [mod (nth modifiers idx)]]
     ^{:key idx} [text/body (:modifier mod)])])

(defn as-value
  [{:keys [base modified modifiers]}]
  [tooltip {:title (r/as-element [as-tooltip base modifiers]) :placement "right"}
   [:span {:class "flex justify-center space-x-1"}
    [text/body modified]
    [text/secondary
     (str "(" (formatters/modifier (cutils/as->modifier modified)) ")")]]])

(defn panel
  [character]
  [:div {:class "bg-white"}
   (let [ability-scores (:ability-scores character)]
     [table-container
      [table {:size "small"}
       [table-head
        [table-row
         (for [as ability-scores]
           ^{:key (first as)} [table-cell {:align "center"}
                               (str/upper-case (name (first as)))])]]
       [table-body
        [table-row
         (for [as ability-scores]
           ^{:key (first as)} [table-cell {:align "center"}
                               [as-value (second as)]])]]]])])
