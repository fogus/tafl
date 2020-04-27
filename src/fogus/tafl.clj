(ns fogus.tafl
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [datascript.core :as d]))


(defn read-table [filename]
  (edn/read-string
   (slurp (io/resource filename))))


