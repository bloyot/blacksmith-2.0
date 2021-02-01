(ns blacksmith.character-utils
  (:require [clojure.string :as str]))

(def asi-mapping {:str "ability score improvement - strength" 
                  :dex "ability score improvement - dexterity" 
                  :con "ability score improvement - constitution"
                  :wis "ability score improvement - wisdom"
                  :int "ability score improvement - intelligence"
                  :cha "ability score improvement - charisma"} )

(def race-speeds {:human 30
                  :elf 30
                  :halfing 25
                  :dwarf 25})

(def ability-scores [:str :dex :con :int :wis :cha])

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

(defn class-level
  "Produce the class, subclass, and level string from the class,
  i.e. Battle Master Fighter 4"
  [class]
  (str
   (str/capitalize (or (:sub-class class) "")) " "
   (str/capitalize (:class class)) " "
   (:level class)))

(defn class-description
  "Combine all classes together into a string"
  [character]
  (str/join
   "/"
   (map class-level (:classes character))))

(defn details
  "Race, alignment, and, background"
  [character]
  (str/join " "
            (for [class [:alignment :race :background]]
              (->> (str/split (class character) #"\b")
                   (map str/capitalize)
                   (str/join)))))

(defn asi-modifiers
  "Return a list of ability score increase information for a given
  ability-score"
  [c ability-score-kw]
  (->> c
      :features
      (filter #(= (ability-score-kw asi-mapping) (:name %1)))
      (reduce (fn [acc {:keys [level name]}]
                (if (get acc level)
                  (update-in acc [level :modifier] inc)
                  {level {:modifier 1
                          :reason (str name "," " level " level)}}))
              {})
      vals))

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

(defn caster-type
  [class sub-class]
  (cond
    (#{:wizard :sorcerer :bard :druid :cleric} class) :full
    (#{:paladin :ranger} class) :half
    (#{:arcane-trickster :eldritch-knight} sub-class) :thrid
    (= :warlock class) :warlock))

(defn char-class->subclass
  "Returns the associated subclass if present for the given class and
  character."
  [character class]
  (->> character
       :classes
       (filter #(= class (keyword (:class %))))
       first
       :sub-class))

(defn class->spell-attribute
  "Maps class to it's primary spellcasting attribute"
  [class]
  (cond
    (= :wizard class) :int
    (#{:druid :cleric :paladin :ranger} class) :wis
    (#{:sorcerer :bard :warlock} class)  :cha))

(defn char-class->level
  [character class]
  (->> character
       :classes
       (filter #(= class (keyword (:class %))))
       first
       :level))
