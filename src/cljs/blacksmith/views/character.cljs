(ns blacksmith.views.character
  (:require [blacksmith.character-utils :as cutils]
            [blacksmith.components.character.ability-scores :as character-ability-scores]
            [blacksmith.components.character.details :as character-details]
             [blacksmith.components.character.features :as character-features]
            [blacksmith.components.character.proficiencies :as character-proficiencies]
            [blacksmith.events :as events]
            [blacksmith.formatters :as formatters]
            [blacksmith.utils :as utils]
            [blacksmith.subs :as subs]
            [reagent-material-ui.core.box :refer [box]]
            [reagent-material-ui.core.button :refer [button]]
            [reagent-material-ui.core.breadcrumbs :refer [breadcrumbs]]
            [reagent-material-ui.core.card :refer [card]]
            [reagent-material-ui.core.card-content :refer [card-content]]
            [reagent-material-ui.core.input-base :refer [input-base]]
            [reagent-material-ui.core.link :refer [link]]
            [reagent-material-ui.core.grid :refer [grid]]
            [reagent-material-ui.core.paper :refer [paper]]
            [reagent-material-ui.core.tabs :refer [tabs]]
            [reagent-material-ui.core.tab :refer [tab]]
            [reagent-material-ui.core.typography :refer [typography]]
            [reagent-material-ui.icons.search :refer [search]]
            [reagent.core :as r]
            [re-frame.core :as rf]))

(def tab-values ["details" "proficiencies" "features" "equipment"])

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

(defn character-tabbed-panel
  [character]
  (let [selected @(rf/subscribe [::subs/character-tab])
        selected-idx (.indexOf tab-values selected)]
    [:div
     [paper {:variant "outlined"}
      [tabs {:indicatorColor "primary"
             :textColor "primary"
             :centered true
             :value selected-idx
             :onChange #(rf/dispatch[::events/character-tab (nth tab-values %2)])}
       [tab {:label "Details"}]
       [tab {:label "Proficiencies"}]
       [tab {:label "Spells and Abilities"}]
       [tab {:label "Equipment"}]]]
     [paper 
      (case selected-idx
        0 [character-details/panel character]
        1 [character-proficiencies/panel character]
        2 [character-features/panel character]
        3 [:div "TODO"])]]))

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
    [character-ability-scores/panel character]]
   [character-tabbed-panel character]])

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
  ;; set the tab based on the hash
  (let [hash (or
              ((into #{} tab-values) (utils/hash))
              "details")]
    (rf/dispatch [::events/character-tab hash]))
  
  ;; render the page
  (let [id (:id route-params)]
    (if-let [character @(rf/subscribe [::subs/character id])]
      [character-view character]
      (do
        (fetch-character id)
        [loading]))))

