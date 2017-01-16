(ns bean-benchmarks.macro-bean
  (:require [bean-benchmarks.beans :as beans]))

(defn ->prefix [class-name-symbol]
  (-> (name class-name-symbol)
      (clojure.string/split #"\.")
      last
      (str "-")))

(defn sym->upcase-1-str
  "Converts the first character of a symbol's name to uppercase, returns a string"
  [sym]
  (let [s (name sym)]
    (str (.toUpperCase (subs s 0 1)) (subs s 1))))

(defn tagged-sym
  "Returns a symbol with the given sym-name and :tag in metadata set to tag-value"
  [sym-name tag-value]
  (vary-meta (symbol sym-name) assoc :tag tag-value))

(defn type-and-field->declarations
  [[type field-name]]
  (let [sym-name (sym->upcase-1-str field-name)]
    `([~(symbol (str "get" sym-name)) [] ~type]
      [~(symbol (str "set" sym-name)) [~type] ~(symbol "void")])))

(defn type-and-field->methods
  [[type field-name] class-name field-num]
  (let [prefix (->prefix class-name)
        sym-name (sym->upcase-1-str field-name)
        value-sym (tagged-sym field-name type)
        this-sym (tagged-sym "this" class-name)
        state-sym (tagged-sym "state" 'objects)]
    `((defn ~(symbol (str prefix "get" sym-name)) [~this-sym]
        (let [~state-sym (.state ~this-sym)]
          (aget ~state-sym ~field-num)))
      (defn ~(symbol (str prefix "set" sym-name)) [~this-sym ~value-sym]
        (let [~state-sym (.state ~this-sym)]
         (aset ~state-sym ~field-num ~value-sym))))))

(defmacro defbean
  "Generates a Java bean. The fields are a set of field type and name."
  [class-name typed-fields]
  {:pre [(vector? typed-fields)
         (even? (count typed-fields))]}
  (let [prefix (->prefix class-name)
        types-and-fields (partition 2 typed-fields)
        field-types (map first types-and-fields)
        field-names (map second types-and-fields)
        method-decls (mapcat type-and-field->declarations types-and-fields)
        method-impls (mapcat (fn [type-and-field i]
                               (type-and-field->methods type-and-field class-name i))
                          types-and-fields (range))
        initial-state (gensym "initial-state")]
    `(do
       (gen-class
        :name ~class-name
        :implements [java.io.Serializable]
        :init "init"
        :state "state"
        :prefix ~prefix
        :constructors {[] []
                       [~@field-types] []}
        :methods [~@method-decls])

       (defn ~(symbol (str prefix "init"))
         ([] [[] (object-array ~(count field-names))])
         ([~@(map symbol field-names)]
          (let [~initial-state (object-array ~(count field-names))]
            ~@(->> (map vector field-names (range))
                   (map (fn [[field idx]] `(aset ~initial-state ~idx ~(symbol field)))))
            [[] ~initial-state])))

       ~@method-impls)))

(defbean bean_benchmarks.MacroGenClass
  [Long timestamp
   String channel
   String title
   String diffUrl
   String user
   Long byteDiff
   String summary
   Boolean isMinor
   Boolean isNew
   Boolean isUnpatrolled
   Boolean isBotEdit
   Boolean isSpecial
   Boolean isTalk])

(def persist-and-load (beans/make-persist-and-load bean_benchmarks.MacroGenClass.
                                                   bean_benchmarks.MacroGenClass))
