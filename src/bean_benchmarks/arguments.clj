(ns bean-benchmarks.arguments
  (:require [clojure.string :as s]
            [clojure.tools.cli :refer [parse-opts]]))

(def cli-options
  [["-n" "--n-test-samples NUMBER" "Number of test samples"
    :default 10000
    :parse-fn #(Integer/parseInt %)
    :validate [#(> % 0) "Number of test samples must be positive"]]
   ["-h" "--help"]])

(defn print-usage [options-summary errors]
  (println (s/join "\n"
                   ["Benchmarks two Clojure implementations of sort-of Java beans."
                    ""
                    "Usage: java -jar bean-benchmarks.jar [arguments]"
                    ""
                    "Options:"
                    options-summary
                    ""
                    (when errors
                      (s/join "\n" (->> (conj errors "")
                                        (cons "Errors:"))))])))

(defn parse [arguments]
  (let [{:keys [options errors summary]} (parse-opts arguments cli-options)]
    (cond
      (:help options) (print-usage summary errors)
      errors (print-usage summary errors)
      :default options)))
