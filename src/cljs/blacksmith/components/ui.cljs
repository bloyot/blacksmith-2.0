(ns blacksmith.components.ui
  (:require [reagent.core :as r]
            [reagent-material-ui.core.accordion :as rmui-accordion]
            [reagent-material-ui.core.accordion-summary :refer [accordion-summary]]
            [reagent-material-ui.core.accordion-details :refer [accordion-details]]
            [reagent-material-ui.icons.expand-more :refer [expand-more]]
            [reagent-material-ui.core.typography :refer [typography]]))

(defn accordion
  [title content]
  [rmui-accordion/accordion {:defaultExpanded true}
   [accordion-summary {:expandIcon (r/as-element [expand-more])}
    [typography {:variant "h5"} title]]
   [accordion-details
    content]])
