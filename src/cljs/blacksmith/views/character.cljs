(ns blacksmith.views.character
  (:require [blacksmith.character-utils :as cutils :refer [saves skills]]
            [blacksmith.events :as events]
            [blacksmith.formatters :as formatters]
            [blacksmith.utils :as utils]
            [blacksmith.subs :as subs]
            [clojure.string :as str]
            [reagent-material-ui.core.accordion :refer [accordion]]
            [reagent-material-ui.core.accordion-summary :refer [accordion-summary]]
            [reagent-material-ui.core.accordion-details :refer [accordion-details]]
            [reagent-material-ui.core.box :refer [box]]
            [reagent-material-ui.core.button :refer [button]]
            [reagent-material-ui.core.breadcrumbs :refer [breadcrumbs]]
            [reagent-material-ui.core.card :refer [card]]
            [reagent-material-ui.core.card-content :refer [card-content]]
            [reagent-material-ui.core.input-base :refer [input-base]]
            [reagent-material-ui.core.link :refer [link]]
            [reagent-material-ui.core.grid :refer [grid]]
            [reagent-material-ui.core.radio :refer [radio]]
            [reagent-material-ui.core.table :refer [table]]
            [reagent-material-ui.core.table-body :refer [table-body]]
            [reagent-material-ui.core.table-cell :refer [table-cell]]
            [reagent-material-ui.core.table-container :refer [table-container]]
            [reagent-material-ui.core.table-head :refer [table-head]]
            [reagent-material-ui.core.table-row :refer [table-row]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.icons.search :refer [search]]
            [reagent-material-ui.icons.expand-more :refer [expand-more]]
            [reagent.core :as r]
            [re-frame.core :as rf]))

(defn error
  []
  [:div "error"])

(defn loading
  []
  [:div "loading"])

(defn header
  [character]
  [:div {:class "flex justify-between items-center"}
   [breadcrumbs
    [link {:color "inherit" :href "/app/character"} "Characters"]
    [typography {:color "textPrimary"} (:name character)]]
   [:div {:class "flex space-x-2"}
    [:a {:href "/app/character"}
     [button {:variant "contained" :color "default"} "Back"]]
    [button {:variant "contained" :color "primary"} "Edit"]]])

(defn as-value
  [[_ as-value]]
  [:span {:class "flex justify-center space-x-1"}
   [typography {:variant "body1"} as-value]
   [typography {:variant "body1" :color "textSecondary"}
    (str "(" (formatters/modifier (cutils/as->modifier as-value)) ")")]])

(defn detail
  [title value]
  [:span {:class "flex space-x-2"}
   [typography {:variant "subtitle2"} title]
   [box {:border 1 :borderColor "grey.500"}
    [:div {:class "mx-2"}
     [typography {:variant "body1"} value]]]])

(defn proficiency-item
  [name prof? stat show-stat? modifier]
  (let [display-name (formatters/proficiency-name name)
        display-stat (formatters/stat stat)]
    [grid {:item true :xs 2}
     [:div {:class "flex space-x-1"}
      [typography {:variant "subtitle2"}
       display-name]
      (when show-stat?
        [typography {:variant "subtitle2" :color "textSecondary"} (str "(" display-stat ")")])]
     [:div {:class "flex justify-start items-center space-x-1"}
      [radio {:checked prof?}]
      [typography {:variant "body1"} (formatters/modifier modifier)]]]))

(defn prof-panel
  [character profs show-stat?]
  (for [[prof stat] profs]
    (let [prof? (cutils/proficient? character prof)
          prof-mod (cutils/char->prof-modifier character prof)]
      ^{:key prof} [proficiency-item prof prof? stat show-stat? prof-mod])))

(defn saves-skills-panel
  [character]
  [grid {:container true :spacing 1}
   [grid {:item true :xs 12}
    [:div {:class "flex justify-center pb-2"}
     [typography {:variant "h6"} "Saving Throws"]]]
   ;; saves
   (prof-panel character saves false)
   ;; skills
   [grid {:item true :xs 12}
    [:div {:class "flex justify-center pb-2"}
     [typography {:variant "h6"} "Skills"]]]
   (prof-panel character skills true)])

(defn details-panel
  [character]
  [grid {:container true :spacing 3}
   [grid {:item true :xs 3}
    [detail "Proficiency Bonus"
     (formatters/modifier (:proficiency-bonus character))]]
   [grid {:item true :xs 3}
    [detail "Armor Class" (:armor-class character)]]
   [grid {:item true :xs 3}
    [detail "Initiative" (formatters/modifier (:initiative character))]]
   [grid {:item true :xs 3}
    [detail "Speed" (str (:speed character) "ft")]]
   [grid {:item true :xs 3}
    [detail "Max Hit Points" (:hit-point-max character)]]
   [grid {:item true :xs 3}
    [detail "Experience" (:experience character)]]
   [grid {:item true :xs 3}
    [detail "Perception" "13"]]
   [grid {:item true :xs 6}
    [:div {:class "flex-col"}
      [typography {:variant "subtitle2"} "Proficiencies"]
      [typography {:variant "body2"}
       (formatters/misc-proficiencies character)]]]
   [grid {:item true :xs 6}
    [:div {:class "flex-col"}
      [typography {:variant "subtitle2"} "Languages"]
      [typography {:variant "body2"}
       (formatters/languages character)]]]])

(defn attributes-panel
  [character]
  [:div {:class "bg-white"}
   (let [bas (:base-ability-scores character)]
     [table-container
      [table {:size "small"}
       [table-head
        [table-row
         (for [as bas]
           ^{:key (first as)} [table-cell {:align "center"}
                               (str/upper-case (name (first as)))])]]
       [table-body
        [table-row
         (for [as bas]
           ^{:key (first as)} [table-cell {:align "center"} [as-value as]])]]]])])

(defn character-accordion
  [title content]
  [accordion {:defaultExpanded true}
   [accordion-summary {:expandIcon (r/as-element [expand-more])}
    [typography {:variant "h5"} title]]
   [accordion-details
    content]])

(defn character-accordion-panel
  [character]
  [:div
   [character-accordion "Details" [details-panel character]]
   [character-accordion "Saves and Skills" [saves-skills-panel character]]
   [character-accordion "Spells and Abilities" "some-other-content"]
   [character-accordion "Equipment" "some-other-content"]])

(defn character-panel
  [character]
  [:div {:class "p-16 space-y-2"}
   [:div {:class "flex justify-between"}
    [typography {:variant "h4"} (:name character)]
    [typography {:variant "subtitle1" :color "textSecondary"}
     (cutils/class-description character)]
    [typography {:variant "subtitle1" :color "textSecondary"}
     (cutils/details character)]]
   [box {:border 1 :borderRadius 3 :borderColor "grey.500"}
    [attributes-panel character]]
   [character-accordion-panel character]])

(defn character-view
  [character]
  [:div {:class "p-4"}
   [header character]
   [character-panel character]])

(defn- fetch-character
  [id]
  (rf/dispatch
   [::events/fetch
    (utils/request :get (str "/v1/character/" id))
    :character id]))

(defn view
  "Render the main view for the single character page"
  [route-params]
  (let [id (:id route-params)]
    (if-let [character @(rf/subscribe [::subs/character id])]
      [character-view character]
      (do
        (fetch-character id)
        [loading]))))

