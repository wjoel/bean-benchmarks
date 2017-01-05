(ns bean-benchmarks.core
  (:require [bean-benchmarks.arguments :as arguments]
            [bean-benchmarks.java-bean :as java-bean]
            [bean-benchmarks.deftype-bean :as deftype-bean]
            [bean-benchmarks.gen-class-bean :as gen-class-bean]
            [bean-benchmarks.deftype-gen-class-bean :as deftype-gen-class-bean]
            [bean-benchmarks.edit-events :as edit-events])
  (:gen-class))

(defn sum-byte-diffs [edits byte-diff-fn]
  (->> (map byte-diff-fn edits)
       (reduce +)))

(def n-tests 10)

(defn main [{:keys [n-test-samples]}]
  (let [test-samples (edit-events/generate-test-samples n-test-samples)
        byte-diff-sum (sum-byte-diffs test-samples ::edit-events/byte-diff)]
    ;; (assert (= byte-diff-sum (deftype-bean/persist-and-load test-samples)))
    ;; (println "deftype results ok")
    (println "Benchmarking Java implementation")
    (dotimes [n n-tests]
      (time (java-bean/persist-and-load test-samples)))

    (println "Benchmarking deftype implementation")
    (dotimes [n n-tests]
      (time (deftype-bean/persist-and-load test-samples)))
    ;(criterium/bench (deftype-bean/persist-and-load test-samples))
    ;; (assert (= byte-diff-sum (gen-class-bean/persist-and-load test-samples)))
    ;; (println "gen-class results ok")
    (println "Benchmarking gen-class implementation")
    (dotimes [n n-tests]
      (time (gen-class-bean/persist-and-load test-samples)))
    ;(criterium/bench (gen-class-bean/persist-and-load test-samples))
    ;; (println "Benchmarking deftype implementation")
    ;; (criterium/report-result (criterium/benchmark (deftype-bean/persist-and-load test-samples) {})
    ;;                          :verbose)
    ;; (println "Benchmarking gen-class implementation")
    ;; (criterium/report-result (criterium/benchmark (gen-class-bean/persist-and-load test-samples) {})
    ;;                          :verbose)
    ;; (assert (= byte-diff-sum (deftype-gen-class-bean/persist-and-load test-samples)))
    ;; (println "deftype-gen-class results ok")
    (println "Benchmarking deftype-gen-class implementation")
    (dotimes [n n-tests]
      (time (deftype-gen-class-bean/persist-and-load test-samples)))

    (println "Done")))

(defn -main [& args]
  (when-let [options (arguments/parse args)]
    (main options)))
