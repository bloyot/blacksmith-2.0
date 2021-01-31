(ns blacksmith.character
  (:require [blacksmith.character-utils :as cutils]
            [blacksmith.db :refer [datasource]]
            [blacksmith.db.character :as char-db]))

(defn join-with
  "Takes a character, and joins them with a sub entity based on the the query,
  storing the result in character map under k. Optionally takes a mapping function
  that can transform the result before storing."
  ([c query k]
   (join-with c query k identity))
  ([c query k transform-fn]
   (assoc c k (transform-fn (query datasource {:id (:id c)})))))

(defn ability-score
  "Create an ability score map for the given ability score keyword
  with :base, :modified, and :modifiers keys."
  [c ability-score-kw]
  (let [base ((keyword (str "base-" (name ability-score-kw))) c)
        asis (cutils/asi-modifiers c ability-score-kw)]
        ;; TODO add others like racial modifiers
    {:base base
     :modified (+ base (reduce + (map :modifier asis)))
     :modifiers asis}))

(defn char->ability-scores
  "Create a map of the character's ability scores.
  "
  [c]
  (let [ability-scores (into {} (for [as cutils/ability-scores]
                                  [as (ability-score c as)]))]
    (-> c
        (assoc :ability-scores ability-scores)
        (dissoc :base-str :base-dex :base-con :base-int :base-wis :base-cha))))

(defn- derived-stats
  "Adds derived stats to the character"
  [c]
  (let [dex (get-in c [:ability-scores :dex :modified])]
    (assoc c
           :proficiency-bonus (cutils/char->proficiency-bonus c)
           :initiative (cutils/as->modifier dex)
           ;; todo add equipment bonus
           :armor-class (+ 10 (cutils/as->modifier dex)))))

(defn rs->character
  "Map a result set to a character"
  [rs]
  (-> rs
     (join-with char-db/get-class-features :features)
     char->ability-scores
     (join-with char-db/get-classes :classes)
     (join-with char-db/get-proficiencies :proficiencies)
     (join-with char-db/get-languages :languages #(map :name %))
     derived-stats))

(defn get-all
  ([] (get-all 20)) ; default limit 20
  ([limit]
   ;; TODO fix the n+1 problem by loading all the sub queries together
   (map rs->character (char-db/get-all datasource {:limit limit}))))

(defn get-one
  "Returns the character, or nil if not found in the db"
  [id]
  (when-let [rs (char-db/get-one datasource {:id id})]
    (rs->character rs)))
