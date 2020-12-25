(ns blacksmith.app
  (:require
   [blacksmith.components.nav :as nav]))

(defn app
  "Create the app shell and do initialization"
  []
  [nav/nav])
