(ns bean-benchmarks.java-bean
  (:require [bean-benchmarks.beans :as beans]))

(def persist-and-load (beans/make-persist-and-load bean_benchmarks.JavaEditBean.
                                                   bean_benchmarks.JavaEditBean))
