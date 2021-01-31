(ns blacksmith.components.character.spells
  (:require [blacksmith.components.text :as text]))

(defn panel
  [character class sub-class]
  [text/subtitle-2 "Spellcasting"])
