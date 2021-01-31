(ns blacksmith.components.character.features
  (:require [blacksmith.macros :refer [seq-with-keys]]
            [blacksmith.character-utils :as cutils]
            [blacksmith.components.character.spells :as spells]
            [blacksmith.components.ui :as ui]
            [blacksmith.components.text :as text]
            [blacksmith.formatters :as formatters]
            [clojure.string :as str]))

(defn feature
  [{:keys [name description level subclass]}]
  [:div {:class "mb-4"}
   [:div {:class "flex space-x-4 items-center"}
    [text/subtitle-2 (str "Level " level)]
    [text/subtitle-1-secondary (formatters/capitalize-all name)]]
   [:div {:class "px-4"} [text/body description]]])

(defn class-panel
  "Render the features for each class in a collapsable panel"
  [character [class features]]
  (let [sorted-features (sort-by :level features)
        sub-class (cutils/char-class->subclass character class)]
    [ui/accordion
     [:div {:class "flex space-x-2"}
      [text/title-5 (formatters/capitalize-all (name class))]
      (when sub-class
        [text/title-5-secondary (str "(" (formatters/capitalize-all sub-class) ")")])]
     [:div
      (seq-with-keys sorted-features #(feature %))
      (when (cutils/caster-type class sub-class)
        [spells/panel character])]]))

(defn panel
  [{:keys [features] :as character}]
  (let [features-by-class (group-by #(keyword (:class %)) features)]
    [:<>
     (seq-with-keys features-by-class #(class-panel character %))]))
