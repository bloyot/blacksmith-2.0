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

(defn format-modifier
  "Takes a modifier value like 2 or -1 and returns the formatted string for display
  like +2 or -1"
  [mod]
  (if (<= 0 mod)
    (str "+" mod)
    (str mod)))

(defn as->modifier
  "Takes an ability score and returns it's modifier (i.e. 2 or -1)"
  [ability-score]
  (if (< ability-score 10)
    (quot (- (- ability-score 1) 10) 2)
    (quot (- ability-score 10) 2)))

(defn char->level
  "Takes a character and returns their overall level based
  on their classes"
  [character]
  (->> character
       :classes
       (map :level)
       (apply +)))

(defn char->proficiency-bonus
  "Takes the character and computes their proficiency bonus based
  on level"
  [character]
  (let [level (char->level character)]
    (+ 2 (quot level 4))))
