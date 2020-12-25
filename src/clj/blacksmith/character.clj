(ns blacksmith.character
  (:refer-clojure :exclude [get])
  (:require [blacksmith.db :as db]))

(def get-all-query (str
                    "select "
                        "pc.id, pc.name, pc.base_str, pc.base_dex, "
                        "pc.base_con, pc.base_int, pc.base_wis, pc.base_cha,"
                        "pc.experience, pc.hit_point_max, r.name as race, "
                        "a.name as alignment, b.name as background "
                    "from player_character pc "
                        "inner join race r on pc.race = r.id "
                        "inner join alignment a on pc.alignment = a.id "
                        "inner join background b on pc.background = b.id "))
(def get-one-query (str
                    "select "
                        "pc.id, pc.name, pc.base_str, pc.base_dex, "
                        "pc.base_con, pc.base_int, pc.base_wis, pc.base_cha,"
                        "pc.experience, pc.hit_point_max, r.name as race, "
                        "a.name as alignment, b.name as background "
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

(def get-saves-query (str "select "
                              "s.name "
                          "from character_save_proficiency csp "
                              "inner join save s on csp.save = s.id "
                          "where player_character = ?"))

(def get-skills-query (str "select "
                               "s.name "
                           "from character_skill_proficiency csp "
                               "inner join skill s on csp.skill = s.id "
                           "where player_character = ?"))

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

(defn rs->character
  "Map a result set to a character"
  [rs]
  (-> rs
     map-base-ability-scores
     (join-with get-classes-query :classes)
     (join-with get-saves-query :save-proficiencies #(flatten (map vals %)))
     (join-with get-skills-query :skill-proficiencies #(flatten (map vals %)))))

(defn get-all
  []
  ;; TODO fix the n+1 problem by loading all the sub queries together
  (map rs->character (db/execute! [get-all-query])))

(defn get-one
  "Returns the character, or nil if not found in the db"
  [id]
  (when-let [rs (db/execute-one! [get-one-query id])]
    (rs->character rs)))
