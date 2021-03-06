(ns fogus.specs
  (:require [clojure.spec.alpha :as s]))

(s/def :book/author     (s/coll-of string?  :kind set? :into #{}))
(s/def :book/genre      (s/coll-of keyword?))
(s/def :book/title      string?)
(s/def :personal/rating number?)
(s/def :personal/tag    (s/coll-of keyword?))

(s/def ::book (s/keys :req [:book/author :book/title]
                      :opt [:book/genre :personal/rating :personal/tag]))
