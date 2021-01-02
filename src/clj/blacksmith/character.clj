(ns blacksmith.character
  (:refer-clojure :exclude [get])
  (:require [blacksmith.character-utils :as cutils]
            [blacksmith.db :as db]))


;; TODO move this db stuff to it's own namespace maybe
;; and/or use an actual sql query building library like honey
;; or hugs
(def get-all-query (str
                    "select "
                        "pc.id, pc.name, pc.base_str, pc.base_dex, "
                        "pc.base_con, pc.base_int, pc.base_wis, pc.base_cha,"
                        "pc.experience, pc.hit_point_max, r.name as race, "
                        "r.speed as speed, a.name as alignment, "
                        "b.name as background "
                    "from player_character pc "
                        "inner join race r on pc.race = r.id "
                        "inner join alignment a on pc.alignment = a.id "
                        "inner join background b on pc.background = b.id "))
(def get-one-query (str
                    "select "
                        "pc.id, pc.name, pc.base_str, pc.base_dex, "
                        "pc.base_con, pc.base_int, pc.base_wis, pc.base_cha,"
                        "pc.experience, pc.hit_point_max, r.name as race, "
                        "r.speed as speed, a.name as alignment, "
                        "b.name as background "
                    "from player_character pc "
                        "inner join race r on pc.race = r.id "
                        "inner join alignment a on pc.alignment = a.id "
                        "inner join background b on pc.background = b.id "
                    "where pc.id = ?"))

(def get-classes-query (str "select "
                                "c.name as class, cc.class_level as level "
                            "from character_class cc "
                                "inner join class c on cc.class = c.id "
                            "where cc.player_character = ?"))

(def get-proficiencies-query
  (str "select "
           "p.name, a.name as attribute, pt.name as type "
       "from character_proficiency cp "
           "inner join proficiency p on cp.proficiency = p.id "
           "left outer join attribute a on p.attribute = a.id "
           "inner join proficiency_type pt on p.proficiency_type = pt.id "
       "where cp.player_character = ?"))

(def get-languages-query
  (str "select "
           "l.name "
       "from character_language cl "
           "inner join language l on cl.language = l.id "
       "where cl.player_character = ?"))

(defn- join-with
  "Takes a character, and joins them with a sub entity based on the the query,
  storing the result in character map under k. Optionally takes a mapping function
  that can transform the result before storing."
  ([c query k]
   (join-with c query k identity))
  ([c query k transform-fn]
   (assoc c k (transform-fn (db/execute! [query (:id c)])))))
 
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
     (join-with get-classes-query :classes)
     (join-with get-proficiencies-query :proficiencies)
     (join-with get-languages-query :languages #(map :name %))
     derived-stats))

(defn get-all
  []
  ;; TODO fix the n+1 problem by loading all the sub queries together
  (map rs->character (db/execute! [get-all-query])))

(defn get-one
  "Returns the character, or nil if not found in the db"
  [id]
  (when-let [rs (db/execute-one! [get-one-query id])]
    (rs->character rs)))
