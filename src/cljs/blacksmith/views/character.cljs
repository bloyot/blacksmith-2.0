(ns blacksmith.views.character
  (:require [blacksmith.character-utils :as cutils]
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

(defn details-panel
  [character]
  [:div {:class "flex-col space-y-8 w-full px-4"}
   [:div {:class "flex justify-between space-x-8"}
    [detail "Proficiency Bonus"
     (formatters/modifier (:proficiency-bonus character))]
    [detail "Armor Class" (:armor-class character)]
    [detail "Initiative" (formatters/modifier (:initiative character))]
    [detail "Speed" (str (:speed character) "ft")]
    [detail "Max Hit Points" (:hit-point-max character)]
    [detail "Exp." (:experience character)]]
   [:div {:class "flex"}
    [:div {:class "flex justify-center w-1/2"}
     [:div {:class "flex-col"}
      [typography {:variant "subtitle2"} "Proficiencies"]
      [typography {:variant "body2"}
       (formatters/misc-proficiencies character)]]]
    [:div {:class "flex justify-center w-1/2"}
     [:div {:class "flex-col"}
      [typography {:variant "subtitle2"} "Languages"]
      [typography {:variant "body2"}
       (formatters/languages character)]]]]])

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
   [character-accordion "Saves and Skills" "some-other-content"]
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

