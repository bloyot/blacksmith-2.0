(ns blacksmith.views.character
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
  [character]
  [:div {:class "flex justify-between items-center"}
   [breadcrumbs
    [link {:color "inherit" :href "/app/character"} "Characters"]
    [typography {:color "textPrimary"} (:name character)]]
   [:div {:class "flex space-x-2"}
    [:a {:href "/app/character"}
     [button {:variant "contained" :color "default"} "Back"]]
    [button {:variant "contained" :color "primary"} "Edit"]]])

(defn character-panel
  [characters]
  [:div {:class "p-16"}
   "foo"])

(defn character-view
  [character]
  [:div {:class "p-4"}
   [header character]
   [character-panel character]])

(defn- fetch-character
  [id]
  (rf/dispatch
   [::events/fetch :character (utils/request :get (str "/v1/character/" id))]))

(defn view
  "Render the main view for the single character page"
  [route-params]
  (let [id (:id route-params)]
    (if-let [character @(rf/subscribe [::subs/character id])]
      [character-view character]
      (do
        (fetch-character id)
        [loading]))))

