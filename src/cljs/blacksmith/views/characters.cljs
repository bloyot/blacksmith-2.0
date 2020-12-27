(ns blacksmith.views.characters
  (:require [blacksmith.character-utils :as cutils]
            [blacksmith.events :as events]
            [blacksmith.utils :as utils]
            [blacksmith.subs :as subs]
            [reagent-material-ui.core.button :refer [button]]
            [reagent-material-ui.core.breadcrumbs :refer [breadcrumbs]]
            [reagent-material-ui.core.card :refer [card]]
            [reagent-material-ui.core.card-content :refer [card-content]]
            [reagent-material-ui.core.input-base :refer [input-base]]
            [reagent-material-ui.core.link :refer [link]]
            [reagent-material-ui.icons.search :refer [search]]
            [reagent-material-ui.core.table :refer [table]]
            [reagent-material-ui.core.table-body :refer [table-body]]
            [reagent-material-ui.core.table-cell :refer [table-cell]]
            [reagent-material-ui.core.table-container :refer [table-container]]
            [reagent-material-ui.core.table-head :refer [table-head]]
            [reagent-material-ui.core.table-row :refer [table-row]]
            [reagent-material-ui.core.typography :refer [typography]]
            [re-frame.core :as rf]))

(defn error
  []
  [:div "error"])

(defn loading
  []
  [:div "loading"])

(defn header
  []
  [:div {:class "flex justify-between items-center"}
   [breadcrumbs
    [link {:color "inherit" :href "/app/character"} "Characters"]]
   [:div {:class "flex space-x-2"}
    [:div {:class "flex items-center bg-gray-200 rounded-lg px-1 space-x-2"}
     [:div [search]]
     [input-base {:placeholder "Search..."}]]
    [button {:variant "contained" :color "primary"} "New"]
    [button {:variant "contained" :color "primary"} "Import"]]])

(defn attributes-panel
  [character]
  (let [as (:base-ability-scores character)]
    [:div {:class "w-128"}
     [table-container
      [table {:size "small"}
       [table-head
        [table-row
         (for [a as]
           ^{:key (first a)} [table-cell (first a)])]]
       [table-body
        [table-row
         (for [a as]
           ^{:key (first a)} [table-cell (second a)])]]]]]))

(defn character-card
  [character]
  [:a {:href (str "/app/character/" (:id character))}
   [:div {:class "m-2 opacity-75 hover:opacity-100 shadow-none hover:shadow-2xl"}
    [card
     [card-content
      [:div {:class "flex justify-between items-start"}
       [typography {:variant "h5"} (:name character)]
       [typography {:variant "subtitle1"} (cutils/details character)]]
      [:div {:class "flex justify-between items-end"}
       [typography {:variant "subtitle1"} (cutils/class-description character)]
       [attributes-panel character]]]]]])

(defn characters-panel
  [characters]
  [:div {:class "p-16"}
   (for [character (:content characters)]
     ^{:key (:id character)} [character-card character])])

(defn characters-view
  []
  (let [characters @(rf/subscribe [::subs/fetch :characters])]
    [:div {:class "p-4"}
     [header]
     [characters-panel characters]]))

(defn- fetch-characters
  []
  (rf/dispatch [::events/fetch :characters (utils/request :get "/v1/character")]))

(defn view
  "Render the main view for the characters page"
  [route-params]
  ;; dispatch the fetch for the data
  (case @(rf/subscribe [::subs/fetch-status :characters])
    nil (do
          (fetch-characters)
          [loading])
    :error [error]
    :in-flight [loading]
    :complete [characters-view]))

