(ns blacksmith.character-utils
  (:require [clojure.string :as str]))

(def saves {:strength :str
            :dexterity :dex
            :constitution :con
            :intelligence :int
            :wisdom :wis
            :charisma :cha})

(def skills (into (sorted-map) {:acrobatics :dex
                                :animal-handling :wis
                                :arcana :int
                                :athletics :str
                                :deception :cha
                                :history :int
                                :insight :wis
                                :intimidation :cha
                                :investigation :int
                                :medicine :wis
                                :nature :wis
                                :perception :cha
                                :performance :cha
                                :persuasion :cha
                                :religion :int
                                :sleight-of-hand :dex
                                :stealth :dex
                                :survival :wis}))

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

(defn proficient?
  "Returns true if the character has the given proficiency"
  [character proficiency]
  (let [normalized-prof (-> proficiency
                            name
                            (str/replace #"-" " ")
                            str/lower-case)]
    (as-> character c
      (:proficiencies c)
      (map :name c)
      (some #(= normalized-prof %) c))))

(defn char->prof-modifier
  "Takes the character and a proficiency, and returns
  the modifier"
  [character proficiency]
  (let [prof? (proficient? character proficiency)
        stat (proficiency (merge saves skills))
        as (get-in character [:base-ability-scores stat])]
    (+ (as->modifier as)
       (if prof? (:proficiency-bonus character) 0))))
