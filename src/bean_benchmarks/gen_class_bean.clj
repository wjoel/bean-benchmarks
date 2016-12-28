(ns bean-benchmarks.gen-class-bean
  (:require [bean-benchmarks.beans :as beans]))

(set! *warn-on-reflection* true)

(gen-class
 :name bean_benchmarks_gen_class_bean.EditGenClass
 :implements [java.io.Serializable]
 :init init
 :state ^"[Ljava.lang.Object;" state
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
   [[] (object-array [timestamp
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
                      isTalk])]))

(defn edit-getTimestamp [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 0))
(defn edit-setTimestamp [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 0 v))
(defn edit-getChannel [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 1))
(defn edit-setChannel [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 1 v))
(defn edit-getTitle [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 2))
(defn edit-setTitle [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 2 v))
(defn edit-getDiffUrl [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 3))
(defn edit-setDiffUrl [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 3 v))
(defn edit-getUser [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 4))
(defn edit-setUser [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 4 v))
(defn edit-getByteDiff [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 5))
(defn edit-setByteDiff [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 5 v))
(defn edit-getSummary [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 6))
(defn edit-setSummary [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 6 v))
(defn edit-getIsMinor [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 7))
(defn edit-setIsMinor [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 7 v))
(defn edit-getIsNew [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 8))
(defn edit-setIsNew [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 8 v))
(defn edit-getIsUnpatrolled [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 9))
(defn edit-setIsUnpatrolled [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 9 v))
(defn edit-getIsBotEdit [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 10))
(defn edit-setIsBotEdit [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 10 v))
(defn edit-getIsSpecial [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 11))
(defn edit-setIsSpecial [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 11 v))
(defn edit-getIsTalk [^bean_benchmarks_gen_class_bean.EditGenClass this]
  (aget ^"[Ljava.lang.Object;" (.state this) 12))
(defn edit-setIsTalk [^bean_benchmarks_gen_class_bean.EditGenClass this v]
  (aset ^"[Ljava.lang.Object;" (.state this) 12 v))

(def persist-and-load (beans/make-persist-and-load bean_benchmarks_gen_class_bean.EditGenClass.))
