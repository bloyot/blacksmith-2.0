(ns blacksmith.config
  (:refer-clojure :exclude [get load])
  (:require [aero.core :as aero]
            [mount.core :as mount]))

;; load once from system environment
(defn env []
  (or (keyword (System/getenv "BLACKSMITH_ENV")) :local))

(defn load*
  []
  (aero/read-config (clojure.java.io/resource "config.edn") {:profile (env)}))

(mount/defstate conf :start (load*))

(defn get [& path]
  (get-in conf (map keyword path)))

