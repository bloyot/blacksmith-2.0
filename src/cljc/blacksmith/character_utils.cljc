(ns blacksmith.character-utils
  (:require [clojure.string :as str]))

(defn class-description
  "Combine all classes together into a string"
  [character]
  (str/join
   "/"
   (map #(str (str/capitalize (:class %)) " " (:level %)) (:classes character))))

(defn details
  "Race, alignment, and, background"
  [character]
  (str/join " "
            (for [class [:alignment :race :background]]
              (->> (str/split (class character) #"\b")
                   (map str/capitalize)
                   (str/join)))))
