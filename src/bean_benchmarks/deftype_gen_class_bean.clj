(ns bean-benchmarks.deftype-gen-class-bean
  (:require [bean-benchmarks.deftype-bean :as deftype-bean]
            [bean-benchmarks.beans :as beans]))

(gen-class
 :name bean_benchmarks_gen_class_bean.EditDeftypeGenClass
 :implements [java.io.Serializable]
 :init init
 :state ^bean_benchmarks.deftype_bean.DeftypeEditEvent state
 :prefix "edit-"
 :constructors {[] []
                [Long
                 String
                 String
                 String
                 String
                 Long
                 String
                 Boolean
                 Boolean
                 Boolean
                 Boolean
                 Boolean
                 Boolean] []}
 :methods [[getTimestamp [] Long]
           [setTimestamp [Long] void]
           [getChannel [] String]
           [setChannel [String] void]
           [getTitle [] String]
           [setTitle [String] void]
           [getDiffUrl [] String]
           [setDiffUrl [String] void]
           [getUser [] String]
           [setUser [String] void]
           [getByteDiff [] Long]
           [setByteDiff [Long] void]
           [getSummary [] String]
           [setSummary [String] void]
           [getIsMinor [] Boolean]
           [setIsMinor [Boolean] void]
           [getIsNew [] Boolean]
           [setIsNew [Boolean] void]
           [getIsUnpatrolled [] Boolean]
           [setIsUnpatrolled [Boolean] void]
           [getIsBotEdit [] Boolean]
           [setIsBotEdit [Boolean] void]
           [getIsSpecial [] Boolean]
           [setIsSpecial [Boolean] void]
           [getIsTalk [] Boolean]
           [setIsTalk [Boolean] void]]
 :main false)

(defn edit-init
  ([] (edit-init 0 nil nil nil nil 0 nil false false false false false false))
  ([timestamp
    channel
    title
    diffUrl
    user
    byteDiff
    summary
    isMinor
    isNew
    isUnpatrolled
    isBotEdit
    isSpecial
    isTalk]
   [[] (deftype-bean/->DeftypeEditEvent
         timestamp
         channel
         title
         diffUrl
         user
         byteDiff
         summary
         isMinor
         isNew
         isUnpatrolled
         isBotEdit
         isSpecial
         isTalk)]))

(defn edit-getTimestamp [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getTimestamp ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setTimestamp [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setTimestamp ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^Long v))
(defn edit-getChannel [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getChannel ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setChannel [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setChannel ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^String v))
(defn edit-getTitle [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getTitle ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setTitle [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setTitle ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^String v))
(defn edit-getDiffUrl [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getDiffUrl ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setDiffUrl [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setDiffUrl ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^String v))
(defn edit-getUser [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getUser ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setUser [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setUser ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^String v))
(defn edit-getByteDiff [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getByteDiff ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setByteDiff [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setByteDiff ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^Long v))
(defn edit-getSummary [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getSummary ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setSummary [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setSummary ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^String v))
(defn edit-getIsMinor [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getIsMinor ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setIsMinor [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setIsMinor ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^Boolean v))
(defn edit-getIsNew [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getIsNew ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setIsNew [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setIsNew ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^Boolean v))
(defn edit-getIsUnpatrolled [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getIsUnpatrolled ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setIsUnpatrolled [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setIsUnpatrolled ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^Boolean v))
(defn edit-getIsBotEdit [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getIsBotEdit ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setIsBotEdit [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setIsBotEdit ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^Boolean v))
(defn edit-getIsSpecial [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getIsSpecial ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setIsSpecial [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setIsSpecial ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^Boolean v))
(defn edit-getIsTalk [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this]
  (.getIsTalk ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this)))
(defn edit-setIsTalk [^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this v]
  (.setIsTalk ^bean_benchmarks.deftype_bean.DeftypeEditEvent (.state ^bean_benchmarks_gen_class_bean.EditDeftypeGenClass this) ^Boolean v))

(def persist-and-load (beans/make-persist-and-load bean_benchmarks_gen_class_bean.EditDeftypeGenClass.
                                                   bean_benchmarks_gen_class_bean.EditDeftypeGenClass))
