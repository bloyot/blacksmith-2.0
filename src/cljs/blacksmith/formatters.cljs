(ns blacksmith.formatters
  (:require [clojure.string :as str]))

(defn capitalize-all
  "Capitalize all words in a string"
  [string]
  (as-> string s
      (str/split s #" ")
      (map str/capitalize s)
      (str/join " " s)))

(defn modifier
  "Takes a modifier value like 2 or -1 and returns the formatted string for display
  like +2 or -1"
  [mod]
  (if (<= 0 mod)
    (str "+" mod)
    (str mod)))

(defn misc-proficiencies
  "Create a formatted string of non-save non-skill proficiencies
  for display."
  [character]
  (let [prof-types #{"weapon" "armor" "tool" "vehicle " "other"}
        profs (->> character
                   :proficiencies
                   (filter #(prof-types (:type %)))
                   (map :name)
                   (str/join ", ")
                   str/capitalize)]
    (if (= "" profs)
      "None"
      profs)))

(defn languages
  "Creates a formatted string of the characters languages for display"
  [character]
  (->> character
      :languages
      (map str/capitalize)
      (str/join ", ")))

(defn proficiency-name
  [prof]
  (as-> prof p
    (name p)
    (str/split p #"-")
    (map str/capitalize p)
    (str/join " " p)
    ;; edge case since Of shouldn't be capitalized
    (str/replace p #"Of" "of")))

(defn stat
  [stat]
  (when stat
    (str/capitalize (name stat))))
