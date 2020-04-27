(ns fogus.specs
  (:require [clojure.spec.alpha :as s]))

(s/def :book/author     (s/coll-of string?  :kind set? :into #{}))
(s/def :book/genre      (s/coll-of keyword? :kind set? :into #{}))
(s/def :book/title      string?)
(s/def :personal/rating number?)
(s/def :personal/tag    (s/coll-of keyword? :kind set? :into #{}))
