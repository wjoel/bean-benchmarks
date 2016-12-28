(ns bean-benchmarks.beans
  (:require [bean-benchmarks.edit-events :as edit-events]))

(set! *warn-on-reflection* true)

(defmacro make-persist-and-load [constructor]
  `(fn [edit-events#]
     (with-open [byte-output-stream# (java.io.ByteArrayOutputStream.)]
       (with-open [object-output-stream# (java.io.ObjectOutputStream. byte-output-stream#)]
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
       (with-open [byte-array-input-stream# (java.io.ByteArrayInputStream. (.toByteArray byte-output-stream#))]
         (with-open [object-input-stream# (java.io.ObjectInputStream. byte-array-input-stream#)]
           (loop [edit-objects# (transient [])]
             (let [edit-object# (try
                                  (.readObject object-input-stream#)
                                  (catch java.io.OptionalDataException _1#
                                    nil)
                                  (catch java.io.EOFException _2#
                                    nil))]
               (if edit-object#
                 (recur (conj! edit-objects# edit-object#))
                 (persistent! edit-objects#)))))))))
