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

(defn build-model [schema enums table]
  (d/db-with
   (empty-model-db schema)
   (concat enums table)))

(defn extract-enums [ks db]
  (let [key-set (set ks)]
    (set (flatten (map (apply juxt key-set) db)))))

(comment

  (def books (read-table "books.edn"))
  (def schema {:book/genre   {:db/valueType   :db.type/ref
                              :db/cardinality :db.cardinality/many}
               :personal/tag {:db/valueType   :db.type/ref
                              :db/cardinality :db.cardinality/many}})
  (def enums  #{:genre/scifi :genre/non-tech-tech :genre/humor :genre/found-drama
                :genre/short-stories :genre/architecture :genre/horror :genre/programming
                :genre/graphic-novel :genre/sports :genre/biology :genre/folklore
                :genre/non-fiction :genre/philosophy :genre/fiction})

  (= enums
     (extract-enums [:book/genre :personal/tag] books))
  
  (def db (build-model schema (map (fn [e] {:db/ident e}) enums) books))

  (d/q '[:find ?title
         :where
         [?bid :book/genre :genre/found-drama]
         [?bid :book/title ?title]]
       db)

)
