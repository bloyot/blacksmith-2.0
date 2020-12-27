(ns blacksmith.character-utils
  (:require [clojure.string :as str]))

(defn full-class-name
  "Combine all classes together into a string"
  [character]
  (str/join
   "/"
   (map #(str (str/capitalize (:class %)) " " (:level %)) (:classes character))))
