(ns fogus.tafl
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [datascript.core :as d]))


(defn read-table [filename]
  (edn/read-string
   (slurp (io/resource filename))))

(defn- empty-model-db [schema]
  (let [conn (d/create-conn schema)]
    (d/db conn)))

(defn build-model [table]
  (d/db-with
   (empty-model-db {})
   table))

(comment

  (def books (read-table "books.edn"))

  (def db (build-model books))

)
