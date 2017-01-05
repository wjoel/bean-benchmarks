(ns bean-benchmarks.beans
  (:require [bean-benchmarks.edit-events :as edit-events]
            [clojure.java.io :as io]))

(defmacro make-persist-and-load [constructor hint]
  `(fn [edit-events# expected-byte-diff-sum#]
     (let [temp-file# (java.io.File/createTempFile "persist" ".bin")]
       (with-open [file-output-stream# (java.io.FileOutputStream. temp-file#)]
         (with-open [object-output-stream# (java.io.ObjectOutputStream. file-output-stream#)]
           (doseq [edit-event# edit-events#]
             (let [obj# (~constructor
                         (::edit-events/timestamp edit-event#)
                         (::edit-events/channel edit-event#)
                         (::edit-events/title edit-event#)
                         (::edit-events/diff-url edit-event#)
                         (::edit-events/user edit-event#)
                         (::edit-events/byte-diff edit-event#)
                         (::edit-events/summary edit-event#)
                         (::edit-events/is-minor edit-event#)
                         (::edit-events/is-new edit-event#)
                         (::edit-events/is-unpatrolled edit-event#)
                         (::edit-events/is-bot-edit edit-event#)
                         (::edit-events/is-special edit-event#)
                         (::edit-events/is-talk edit-event#))]
               (.writeObject object-output-stream# obj#))))
         (let [file-size-kb# (int (/ (.length (io/file temp-file#)) 1024))]
           (with-open [byte-array-input-stream# (java.io.FileInputStream. temp-file#)]
             (with-open [object-input-stream# (java.io.ObjectInputStream. byte-array-input-stream#)]
               (let [edit-objects# (loop [objects# (transient [])]
                                     (let [edit-object# (try
                                                          (.readObject object-input-stream#)
                                                          (catch java.io.OptionalDataException _1#
                                                            nil)
                                                          (catch java.io.EOFException _2#
                                                            nil))]
                                       (if edit-object#
                                         (recur (conj! objects# edit-object#))
                                         (persistent! objects#))))
                     byte-diff-sum# (->> (map #(.getByteDiff ^{:tag ~hint} %) edit-objects#)
                                         (reduce +))]
                 (.delete temp-file#)
                 (do (assert (= byte-diff-sum# expected-byte-diff-sum#))
                     file-size-kb#)))))))))
