(ns blacksmith.components.ui
  (:require [reagent-material-ui.core.accordion :refer [accordion]]
            [reagent-material-ui.core.accordion-summary :refer [accordion-summary]]
            [reagent-material-ui.core.accordion-details :refer [accordion-details]]
            [reagent-material-ui.core.typography :refer [typography]]))

(defn accordion
  [title content]
  [accordion {:defaultExpanded true}
   [accordion-summary {:expandIcon (r/as-element [expand-more])}
    [typography {:variant "h5"} title]]
   [accordion-details
    content]])
