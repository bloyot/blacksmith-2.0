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

(defn as-tooltip-modifier
  [modifier]
  (let [mod-text (formatters/modifier (:modifier modifier))
        reason-text (:reason modifier)]
    [text/body (str mod-text " " reason-text)]))

(defn as-tooltip
  [base modifiers]
  [:<>
   (cons ^{:key -1} [text/body (str "Base: " base)]
         (if (seq modifiers)
           (seq-with-keys modifiers #(as-tooltip-modifier %))
           ^{:key 0} [text/body "No modifiers"]))])

(defn as-value
  [{:keys [base modified modifiers]}]
  [tooltip {:title (r/as-element [as-tooltip base modifiers]) :placement "right"}
   [:span {:class "flex justify-center space-x-1"}
    [text/body modified]
    [text/secondary
     (str "(" (formatters/modifier (cutils/as->modifier modified)) ")")]]])

(defn as-cell
  [content]
  [table-cell {:align "center"} content])

(defn panel
  [character]
  [:div {:class "bg-white"}
   (let [ability-scores (:ability-scores character)]
     [table-container
      [table {:size "small"}
       [table-head
        [table-row
         (seq-with-keys ability-scores #(as-cell (str/upper-case (name (first %)))))]]
       [table-body
        [table-row
         (seq-with-keys ability-scores #(as-cell (as-value (second %))))]]]])])
