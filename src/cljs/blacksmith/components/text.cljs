(ns blacksmith.components.text
  (:require [reagent-material-ui.core.typography :refer [typography]]))

;; helpers for text styles
(defn text
  [content & [variant color]]
  [typography {:variant (or variant "body1")
               :color (or color "textPrimary")}
   content])

(defn body
  [content]
  [text content "body1"])

(defn secondary
  [content]
  [text content "body1" "textSecondary"])

(defn title-5
  [content]
  [text content "h5"])

(defn title-5-secondary
  [content]
  [text content "h5" "textSecondary"])

(defn title-6
  [content]
  [text content "h6"])

(defn subtitle-1
  [content]
  [text content "subtitle1"])

(defn subtitle-1-secondary
  [content]
  [text content "subtitle1" "textSecondary"])

(defn subtitle-2
  [content]
  [text content "subtitle2"])
