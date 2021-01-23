(ns blacksmith.db.migration
  (:require [blacksmith.config :as conf]
            [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defn load-config [migrations-path]
  {:datastore  (jdbc/sql-database (conf/get :db-spec))
   :migrations (jdbc/load-resources migrations-path)})

(defn load-config-fixtures []
  {:datastore  (jdbc/sql-database (conf/get :db-spec) {:migrations-table "ragtime_migrations_fixtures"})
   :migrations (jdbc/load-resources "fixtures")})

(defn migrate
  ([]
   (repl/migrate (load-config "sql/migrations")))
  ([migrations-path]
   (repl/migrate (load-config migrations-path))))

(defn rollback
  ([]
   (repl/rollback (load-config "sql/migrations")))
  ([migrations-path]
   (repl/rollback (load-config migrations-path))))

(defn rollback-all []
  (let [config (load-config "sql/migrations")]
    (repl/rollback config (count (:migrations config )))))

(defn migrate-fixtures
  "Loads fixtures data for test and development"
  []
  (repl/migrate (load-config-fixtures)))

(defn rollback-fixtures
  "Loads fixtures data for test and development"
  []
  (repl/rollback (load-config-fixtures)))

