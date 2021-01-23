(ns blacksmith.db.character
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/hugsql/character.sql")

