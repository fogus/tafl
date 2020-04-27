(ns fogus.tafl-test
  (:require [clojure.test :refer :all]
            [fogus.tafl :as tafl]
            [fogus.specs :as specs]
            [clojure.spec.alpha :as s]))

(deftest test-sample-data-conformance
  (testing "that the sample data adheres to the format that the tests expect."
    (let [data (tafl/read-table "books.edn")]
      (is (s/valid? (s/coll-of :fogus.specs/book) data)))))
