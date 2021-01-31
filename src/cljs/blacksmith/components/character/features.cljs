(ns blacksmith.components.character.features
  (:require [blacksmith.macros :refer [seq-with-keys]]
            [blacksmith.components.ui :as ui]
            [clojure.string :as str]))

(defn class-panel-content
  [features]
  [:div
   (seq-with-keys features #(identity [:div (:name %)]))])

(defn class-panel
  "Render the features for each class in a collapsable panel"
  [[class-name features]]
  [ui/accordion (str/capitalize (name class-name)) [class-panel-content features]])

(defn panel
  [{:keys [features] :as character}]
  (let [features-by-class (group-by #(keyword (:class %)) features)]
    [:<>
     (seq-with-keys features-by-class #(class-panel %))]))
