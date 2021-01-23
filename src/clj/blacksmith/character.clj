(ns blacksmith.character
  (:refer-clojure :exclude [get])
  (:require [blacksmith.character-utils :as cutils]
            [blacksmith.db :refer [datasource]]
            [blacksmith.db.character :as char-db]))

(defn- join-with
  "Takes a character, and joins them with a sub entity based on the the query,
  storing the result in character map under k. Optionally takes a mapping function
  that can transform the result before storing."
  ([c query k]
   (join-with c query k identity))
  ([c query k transform-fn]
   (assoc c k (transform-fn (query datasource {:id (:id c)})))))
 
(defn- map-base-ability-scores
  "Put the characters base stats into a map"
  [c]
  (-> c
      (assoc :base-ability-scores {:str (:base-str c)
                                   :dex (:base-dex c)
                                   :con (:base-con c)
                                   :int (:base-int c)
                                   :wis (:base-wis c)
                                   :cha (:base-cha c)})
      (dissoc :base-str :base-dex :base-con :base-int :base-wis :base-cha)))

(defn- derived-stats
  "Adds derived stats to the character"
  [c]
  (let [dex (get-in c [:base-ability-scores :dex])]
    (assoc c :proficiency-bonus (cutils/char->proficiency-bonus c)
           :initiative (cutils/as->modifier dex)
           ;; todo add equipment bonus
           :armor-class (+ 10 (cutils/as->modifier dex)))))

(defn rs->character
  "Map a result set to a character"
  [rs]
  (-> rs
     map-base-ability-scores
     (join-with char-db/get-classes :classes)
     (join-with char-db/get-proficiencies :proficiencies)
     (join-with char-db/get-languages :languages #(map :name %))
     (join-with char-db/get-class-features :features)
     derived-stats))

(defn get-all
  []
  ;; TODO fix the n+1 problem by loading all the sub queries together
  (map rs->character (char-db/get-all datasource)))

(defn get-one
  "Returns the character, or nil if not found in the db"
  [id]
  (when-let [rs (char-db/get-one datasource {:id id})]
    (rs->character rs)))
