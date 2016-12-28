(ns bean-benchmarks.core-test
    (:require [clojure.test :refer :all]
              [bean-benchmarks.core :refer :all]))

(deftest test-tests
  (testing "Whether or not you've created tests"
    (is (= 0 1))))
