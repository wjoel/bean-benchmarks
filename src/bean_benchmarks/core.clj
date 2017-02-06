(ns bean-benchmarks.core
  (:require [bean-benchmarks.arguments :as arguments]
            [bean-benchmarks.java-bean :as java-bean]
            [bean-benchmarks.primitive-java-bean :as primitive-java-bean]
            [bean-benchmarks.deftype-bean :as deftype-bean]
            [bean-benchmarks.gen-class-bean :as gen-class-bean]
            [bean-benchmarks.deftype-gen-class-bean :as deftype-gen-class-bean]
            [bean-benchmarks.macro-bean :as macro-bean]
            [bean-benchmarks.defbean-bean :as defbean-bean]
            [bean-benchmarks.edit-events :as edit-events])
  (:gen-class))

(defn sum-byte-diffs [edits byte-diff-fn]
  (->> (map byte-diff-fn edits)
       (reduce +)))

(defn mean [values]
  (/ (apply + values) (count values)))

(defn standard-deviation [values]
  (let [mean-value (mean values)]
    (->> (map #(- % mean-value) values)
         (map #(* % %))
         mean
         Math/sqrt)))

(defn print-stats [name file-size measurements]
  (println (format "| %s | %d KB | %.4f s | %.4f s |"
                   name file-size (mean measurements) (standard-deviation measurements))))

(def n-tests 10)

(defn measure [n-measures f]
  (for [n (range n-measures)]
    (let [start-time (. System nanoTime)
          return-value (f)
          end-time (. System nanoTime)]
      [(/ (double (- end-time start-time)) 1000000000.0) return-value])))

(defn measure-and-print-stats [name n-measures f]
  (let [measures (measure n-measures f)
        file-size (-> measures first second)
        evaluation-times (map first measures)]
    (print-stats name file-size evaluation-times)))

(defn main [{:keys [n-test-samples]}]
  (let [test-samples (edit-events/generate-test-samples n-test-samples)
        byte-diff-sum (sum-byte-diffs test-samples ::edit-events/byte-diff)]
    (println "| Implementation | Size on disk (KB) | Mean execution time (s) | Standard deviation (s) |")
    (println "|----------------+-------------------+-------------------------+------------------------|")
    (measure-and-print-stats "Java" n-tests #(java-bean/persist-and-load test-samples byte-diff-sum))
    (measure-and-print-stats "Java with primitive types" n-tests #(primitive-java-bean/persist-and-load test-samples byte-diff-sum))
    (measure-and-print-stats "deftype" n-tests #(deftype-bean/persist-and-load test-samples byte-diff-sum))
    (measure-and-print-stats "gen-class" n-tests #(gen-class-bean/persist-and-load test-samples byte-diff-sum))
    (measure-and-print-stats "deftype-gen-class" n-tests #(deftype-gen-class-bean/persist-and-load test-samples byte-diff-sum))
    (measure-and-print-stats "macro-bean" n-tests #(macro-bean/persist-and-load test-samples byte-diff-sum))
    (measure-and-print-stats "defbean-bean" n-tests #(defbean-bean/persist-and-load test-samples byte-diff-sum))))

(defn -main [& args]
  (when-let [options (arguments/parse args)]
    (main options)))
