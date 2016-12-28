(ns bean-benchmarks.core
  (:require [bean-benchmarks.arguments :as arguments]
            [bean-benchmarks.deftype-bean :as deftype-bean]
            [bean-benchmarks.gen-class-bean :as gen-class-bean]
            [bean-benchmarks.edit-events :as edit-events]
            [criterium.core :as criterium])
  (:gen-class))

(set! *warn-on-reflection* true)

(defn sum-byte-diffs [edits byte-diff-fn]
  (->> (map byte-diff-fn edits)
       (reduce +)))

(defn main [{:keys [n-test-samples]}]
  (let [test-samples (edit-events/generate-test-samples n-test-samples)
        byte-diff-sum (sum-byte-diffs test-samples ::edit-events/byte-diff)]
    (assert (= byte-diff-sum (sum-byte-diffs (deftype-bean/persist-and-load test-samples)
                                             (fn [^bean_benchmarks.deftype_bean.DeftypeEditEvent e]
                                               (.getByteDiff e)))))
    (println "deftype results ok")
    (println "Benchmarking deftype implementation")
    (criterium/bench (deftype-bean/persist-and-load test-samples))
    (assert (= byte-diff-sum (sum-byte-diffs (gen-class-bean/persist-and-load test-samples)
                                             (fn [^bean_benchmarks_gen_class_bean.EditGenClass e]
                                               (.getByteDiff e)))))
    (println "gen-class results ok")
    (println "Benchmarking gen-class implementation")
    (criterium/bench (gen-class-bean/persist-and-load test-samples))
    ;; (println "Benchmarking deftype implementation")
    ;; (criterium/report-result (criterium/benchmark (deftype-bean/persist-and-load test-samples) {})
    ;;                          :verbose)
    ;; (println "Benchmarking gen-class implementation")
    ;; (criterium/report-result (criterium/benchmark (gen-class-bean/persist-and-load test-samples) {})
    ;;                          :verbose)
    (println "Done")))

(defn -main [& args]
  (when-let [options (arguments/parse args)]
    (main options)))
