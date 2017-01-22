(ns bean-benchmarks.gen-class-bean
  (:require [bean-benchmarks.beans :as beans]))

(gen-class
 :name bean_benchmarks.EditGenClass
 :implements [java.io.Serializable]
 :init init
 :state state
 :prefix "edit-"
 :constructors {[] []
                [long
                 String
                 String
                 String
                 String
                 long
                 String
                 boolean
                 boolean
                 boolean
                 boolean
                 boolean
                 boolean] []}
 :methods [[getTimestamp [] long]
           [setTimestamp [long] void]
           [getChannel [] String]
           [setChannel [String] void]
           [getTitle [] String]
           [setTitle [String] void]
           [getDiffUrl [] String]
           [setDiffUrl [String] void]
           [getUser [] String]
           [setUser [String] void]
           [getByteDiff [] long]
           [setByteDiff [long] void]
           [getSummary [] String]
           [setSummary [String] void]
           [getIsMinor [] boolean]
           [setIsMinor [boolean] void]
           [getIsNew [] boolean]
           [setIsNew [boolean] void]
           [getIsUnpatrolled [] boolean]
           [setIsUnpatrolled [boolean] void]
           [getIsBotEdit [] boolean]
           [setIsBotEdit [boolean] void]
           [getIsSpecial [] boolean]
           [setIsSpecial [boolean] void]
           [getIsTalk [] boolean]
           [setIsTalk [boolean] void]])

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
   [[] (object-array [(long-array [timestamp byteDiff])
                      (object-array [channel title diffUrl user summary])
                      (boolean-array [isMinor isNew isUnpatrolled
                                      isBotEdit isSpecial isTalk])])]))

(defn edit-getTimestamp [^bean_benchmarks.EditGenClass this]
  (aget ^longs (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 0) 0))
(defn edit-setTimestamp [^bean_benchmarks.EditGenClass this v]
  (aset ^longs (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 0) 0 ^long v))
(defn edit-getChannel [^bean_benchmarks.EditGenClass this]
  (aget ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 0))
(defn edit-setChannel [^bean_benchmarks.EditGenClass this v]
  (aset ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 0 ^String v))
(defn edit-getTitle [^bean_benchmarks.EditGenClass this]
  (aget ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 1))
(defn edit-setTitle [^bean_benchmarks.EditGenClass this v]
  (aset ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 1 ^String v))
(defn edit-getDiffUrl [^bean_benchmarks.EditGenClass this]
  (aget ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 2))
(defn edit-setDiffUrl [^bean_benchmarks.EditGenClass this v]
  (aset ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 2 ^String v))
(defn edit-getUser [^bean_benchmarks.EditGenClass this]
  (aget ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 3))
(defn edit-setUser [^bean_benchmarks.EditGenClass this v]
  (aset ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 3 ^String v))
(defn edit-getByteDiff [^bean_benchmarks.EditGenClass this]
  (aget ^longs (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 0) 1))
(defn edit-setByteDiff [^bean_benchmarks.EditGenClass this v]
  (aset ^longs (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 0) 1 ^long v))
(defn edit-getSummary [^bean_benchmarks.EditGenClass this]
  (aget ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 4))
(defn edit-setSummary [^bean_benchmarks.EditGenClass this v]
  (aset ^objects (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 1) 4 ^String v))
(defn edit-getIsMinor [^bean_benchmarks.EditGenClass this]
  (aget ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 0))
(defn edit-setIsMinor [^bean_benchmarks.EditGenClass this v]
  (aset ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 0 ^boolean v))
(defn edit-getIsNew [^bean_benchmarks.EditGenClass this]
  (aget ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 1))
(defn edit-setIsNew [^bean_benchmarks.EditGenClass this v]
  (aset ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 1 ^boolean v))
(defn edit-getIsUnpatrolled [^bean_benchmarks.EditGenClass this]
  (aget ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 2))
(defn edit-setIsUnpatrolled [^bean_benchmarks.EditGenClass this v]
  (aset ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 2 ^boolean v))
(defn edit-getIsBotEdit [^bean_benchmarks.EditGenClass this]
  (aget ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 3))
(defn edit-setIsBotEdit [^bean_benchmarks.EditGenClass this v]
  (aset ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 3 ^boolean v))
(defn edit-getIsSpecial [^bean_benchmarks.EditGenClass this]
  (aget ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 4))
(defn edit-setIsSpecial [^bean_benchmarks.EditGenClass this v]
  (aset ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 4 ^boolean v))
(defn edit-getIsTalk [^bean_benchmarks.EditGenClass this]
  (aget ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 5))
(defn edit-setIsTalk [^bean_benchmarks.EditGenClass this v]
  (aset ^booleans (aget ^objects (.state ^bean_benchmarks.EditGenClass this) 2) 5 ^boolean v))

(def persist-and-load (beans/make-persist-and-load bean_benchmarks.EditGenClass.
                                                   bean_benchmarks.EditGenClass))
