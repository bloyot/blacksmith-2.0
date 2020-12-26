(ns blacksmith.views.characters
  (:require [blacksmith.events :as events]
            [blacksmith.utils :as utils]
            [blacksmith.subs :as subs]
            [re-frame.core :as rf]))

(defn error
  []
  [:div "error"])

(defn loading
  []
  [:div "loading"])

(defn character-view
  []
  (let [characters @(rf/subscribe [::subs/fetch :characters])]
    [:div {:class "p-4"}
     (for [character (:content characters)]
       ^{:key (:id character)} [:p (str "Character Name: " (:name character))])]))

(defn view
  "Render the main view for the characters page"
  [route-params]
  ;; dispatch the fetch for the data
  (rf/dispatch [::events/fetch :characters (utils/request :get "/v1/character")])
  (case @(rf/subscribe [::subs/fetch-status :characters])
    :error [error]
    (:in-flight nil) [loading]
    :complete [character-view]))

