(ns bean-benchmarks.defbean-bean
  (:require [clj-bean.core :refer [defbean]]
            [bean-benchmarks.beans :as beans]))

(defbean bean_benchmarks.DefBeanClass
  [[long timestamp]
   [String channel]
   [String title]
   [String diffUrl]
   [String user]
   [long byteDiff]
   [String summary]
   [boolean isMinor]
   [boolean isNew]
   [boolean isUnpatrolled]
   [boolean isBotEdit]
   [boolean isSpecial]
   [boolean isTalk]])

(def persist-and-load (beans/make-persist-and-load bean_benchmarks.DefBeanClass.
                                                   bean_benchmarks.DefBeanClass))
