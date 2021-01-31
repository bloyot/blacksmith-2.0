(ns blacksmith.components.text
  (:require [reagent-material-ui.core.typography :refer [typography]]))

(defn body
  [content]
  [typography {:variant "body1"} content])

(defn secondary
  [content]
  [typography {:variant "body1" :color "textSecondary"} content])
