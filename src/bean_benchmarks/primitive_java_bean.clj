(ns bean-benchmarks.primitive-java-bean
  (:require [bean-benchmarks.beans :as beans]))

(def persist-and-load (beans/make-persist-and-load bean_benchmarks.PrimitiveJavaEditBean.
                                                   bean_benchmarks.PrimitiveJavaEditBean))
