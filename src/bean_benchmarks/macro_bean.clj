(ns bean-benchmarks.macro-bean
  (:require [bean-benchmarks.beans :as beans]))

(defn ->prefix [class-name-symbol]
  (-> (name class-name-symbol)
      (clojure.string/split #"\.")
      last
      (str "-")))

(def primitives
  {'boolean {:default false :array-type 'booleans}
   'byte {:default (byte 0) :array-type 'bytes}
   'char {:default (char \u0000) :array-type 'chars}
   'short {:default (short 0) :array-type 'shorts}
   'int {:default (int 0) :array-type 'ints}
   'long {:default (long 0) :array-type 'longs}
   'float {:default (float 0) :array-type 'floats}
   'double {:default (double 0) :array-type 'doubles}})

(defn default-value [type]
  (get-in primitives [type :default] nil))

(defn array-type [type]
  (get-in primitives [type :array-type] 'objects))

(defn primitive? [type]
  (contains? primitives type))

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

(defn type-and-field->accessors
  [[type field-name] class-name [type-array-index field-index]]
  (let [prefix (->prefix class-name)
        sym-name (sym->upcase-1-str field-name)
        value-sym (tagged-sym field-name type)
        this-sym (tagged-sym "this" class-name)
        state-sym (tagged-sym "state" 'objects)
        array-sym (tagged-sym "type-array" (array-type type))]
    `((defn ~(symbol (str prefix "get" sym-name)) [~this-sym]
        (let [~state-sym (.state ~this-sym)
              ~array-sym (aget ~state-sym ~type-array-index)]
          (aget ~array-sym ~field-index)))
      (defn ~(symbol (str prefix "set" sym-name)) [~this-sym ~(symbol field-name)]
        (let [~state-sym (.state ~this-sym)
              ~array-sym (aget ~state-sym ~type-array-index)]
          (aset ~array-sym ~field-index ~value-sym))))))

(defn primitive-field->initializer [[type fields]]
  (let [array-type (symbol (str "clojure.core/" (name type) "-array"))]
    `(~array-type [~@fields])))

(defn initial-state-value [types-and-fields]
  (let [type->fields (into {} types-and-fields)
        non-primitives (->> (filter #(not (primitive? (first %))) types-and-fields)
                            (map second)
                            sort)
        primitive-types-and-fields (->> (filter #(primitive? (first %)) types-and-fields)
                                        (map (fn [[type field-name]]
                                               {type [field-name]})))
        primitive-type->fields (apply merge-with concat primitive-types-and-fields)
        type->index (->> (map vector (sort (keys primitive-type->fields)) (range))
                         (into {'object (count primitive-type->fields)}))
        primitive-inits (->> (map primitive-field->initializer primitive-type->fields)
                             (sort-by first))
        field-name->coordinate (->> (map (fn [[type field-name]]

                                           (let [type-fields (or (get primitive-type->fields type)
                                                                 non-primitives)]
                                             {field-name [(type->index (if (primitive? type)
                                                                         type
                                                                         'object))
                                                          (.indexOf ^java.util.List type-fields
                                                                    field-name)]}))
                                         types-and-fields)
                                    (into {}))
        object-inits `(object-array [~@non-primitives])]
    [field-name->coordinate (concat primitive-inits [object-inits])]))

(defmacro defbean
  "Generates a Java bean. The fields are a set of field type and name."
  [class-name types-and-fields]
  {:pre [(vector? types-and-fields)
         (every? vector? types-and-fields)
         (every? (comp even? count) types-and-fields)]}
  (let [prefix (->prefix class-name)
        field-types (map first types-and-fields)
        field-names (map second types-and-fields)
        val (initial-state-value types-and-fields)
        [field-name->coordinate initial-state-initializer] (initial-state-value types-and-fields)
        method-decls (mapcat type-and-field->declarations types-and-fields)
        method-impls (mapcat (fn [[type field-name :as type-and-field]]
                               (type-and-field->accessors type-and-field class-name
                                                          (field-name->coordinate field-name)))
                             types-and-fields)
        initial-state (gensym "initial-state")
        init-sym (symbol (str prefix "init"))
        null-constructor-values (map default-value field-types)]
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

       (defn ~init-sym
         ([] [[] (~init-sym ~@null-constructor-values)])
         ([~@(map symbol field-names)]
          [[] (object-array [~@initial-state-initializer])]))

       ~@method-impls)))

(defbean bean_benchmarks.MacroGenClass
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

(def persist-and-load (beans/make-persist-and-load bean_benchmarks.MacroGenClass.
                                                   bean_benchmarks.MacroGenClass))
