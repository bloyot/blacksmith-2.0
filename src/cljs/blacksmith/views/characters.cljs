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
            [reagent-material-ui.core.grid :refer [grid]]
            [reagent-material-ui.icons.search :refer [search]]
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

(defn character-card
  [character]
  [:a {:href (str "/app/character/" (:id character))}
   [:div {:class "m-2 opacity-75 hover:opacity-100 shadow-none hover:shadow-2xl"}
    [card
     [card-content
      [typography {:variant "h5"} (:name character)]
      [typography {:variant "subtitle1"} (cutils/class-description character)]]]]])

(defn characters-panel
  [characters]
  [:div {:class "p-16"}
   [grid {:container true :spacing 3}
    (for [character (:content characters)]
      ^{:key (:id character)}
      [grid {:item true :xs 3} [character-card character]])]])

(defn characters-view
  []
  (let [characters @(rf/subscribe [::subs/fetch :characters])]
    [:div {:class "p-4"}
     [header]
     [characters-panel characters]]))

(defn- fetch-characters
  []
  (rf/dispatch [::events/fetch
                (utils/request :get "/v1/character")
                :characters]))

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

