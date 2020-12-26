(ns blacksmith.core
  (:require [blacksmith.app :refer [app]]
            [blacksmith.events :as events]
            [bidi.bidi :as bidi]
            [pushy.core :as pushy]
            [reagent.core :as r]
            [reagent.dom :as rdom]
            [re-frame.core :as rf]))

(def app-routes
  ["/app" {"/character" :characters
           "/character/" {[:id] :character}}])

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (rdom/render [app] (.getElementById js/document "app")))

(defn set-page! [match]
  (rf/dispatch [::events/view (:handler match) (:route-params match)]))

(def history
  (pushy/pushy set-page! (partial bidi/match-route app-routes)))

(defn ^:export main
  "Run application startup logic."
  []
  (rf/clear-subscription-cache!)
  (pushy/start! history)
  (render))
