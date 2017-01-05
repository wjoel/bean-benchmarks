(defproject bean-benchmarks "0.1.0-SNAPSHOT"
  :description "Benchmarking of Java bean implementations in Clojure"
  :url "https://github.com/wjoel/bean-benchmarks"
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[clojure-future-spec "1.9.0-alpha14"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/test.check "0.9.0"]
                 [org.clojure/tools.cli "0.3.5"]]
  :prep-tasks [["compile" "bean-benchmarks.deftype-bean"] "javac" "compile"]
  :aot :all
  :main bean-benchmarks.core
  :java-source-paths ["java"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
