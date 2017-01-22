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

(defn array [type]
  (get-in primitives [type :array-type] 'objects))

(defn primitive? [type]
  (contains? primitives type))

(defn canonical [type]
  (if (primitive? type)
    type
    'object))

(defn sym->upcase-1-str
  "Converts the first character of a symbol's name to uppercase, returns a string"
  [sym]
  (let [s (name sym)]
    (str (.toUpperCase (subs s 0 1)) (subs s 1))))

(defn tagged-sym
  "Returns a symbol with the given sym-name and :tag in metadata set to tag-value"
  [sym-name tag-value]
  (vary-meta (symbol sym-name) assoc :tag tag-value))

(defn typed-field->declarations
  [[type name]]
  (let [sym-name (sym->upcase-1-str name)]
    `([~(symbol (str "get" sym-name)) [] ~type]
      [~(symbol (str "set" sym-name)) [~type] ~(symbol "void")])))

(defn typed-field->accessors
  [[type name] class-name [type-array-index field-index]]
  (let [prefix (->prefix class-name)
        sym-name (sym->upcase-1-str name)
        value-sym (tagged-sym name type)
        this-sym (tagged-sym "this" class-name)
        state-sym (tagged-sym "state" 'objects)
        array-sym (tagged-sym "type-array" (array type))]
    `((defn ~(symbol (str prefix "get" sym-name)) [~this-sym]
        (let [~state-sym (.state ~this-sym)
              ~array-sym (aget ~state-sym ~type-array-index)]
          (aget ~array-sym ~field-index)))
      (defn ~(symbol (str prefix "set" sym-name)) [~this-sym ~(symbol name)]
        (let [~state-sym (.state ~this-sym)
              ~array-sym (aget ~state-sym ~type-array-index)]
          (aset ~array-sym ~field-index ~value-sym))))))

(defn coordinate-map [type->fields]
  (let [type->state-index (->> (map vector (sort (keys type->fields)) (range))
                               (into {}))
        ->type-index (fn [[type name]]
                       (.indexOf ^java.util.List (type->fields (canonical type)) name))]
    (reduce (fn [m [type fields]]
              (reduce (fn [m field]
                        (assoc m field [(type->state-index type)
                                        (->type-index [type field])]))
                      m fields))
            {} type->fields)))

(defn typed-fields->initializer [[type fields]]
  (let [array-type (symbol (str "clojure.core/" (name type) "-array"))]
    `(~array-type [~@fields])))

(defn initial-state-value [type->fields]
  (->> (map typed-fields->initializer type->fields)
       (sort-by first)))

(defmacro defbean
  "Generates a Java bean. The fields are a set of field type and name."
  [class-name typed-fields]
  {:pre [(vector? typed-fields)
         (every? vector? typed-fields)
         (every? (comp even? count) typed-fields)]}
  (let [prefix (->prefix class-name)
        types (map first typed-fields)
        names (map second typed-fields)
        object-fields (->> (filter #(not (primitive? (first %))) typed-fields)
                           (map second)
                           sort)
        type->fields (->> (filter #(primitive? (first %)) typed-fields)
                          (reduce (fn [m [type name]]
                                    (assoc m type (conj (m type) name)))
                                  {'object object-fields}))
        initial-state-initializer (initial-state-value type->fields)
        name->coordinate (coordinate-map type->fields)
        method-decls (mapcat typed-field->declarations typed-fields)
        method-impls (mapcat (fn [[type name :as typed-field]]
                               (typed-field->accessors typed-field class-name
                                                       (name->coordinate name)))
                             typed-fields)
        initial-state (gensym "initial-state")
        init-sym (symbol (str prefix "init"))
        null-constructor-values (map default-value types)]
    `(do
       (gen-class
        :name ~class-name
        :implements [java.io.Serializable]
        :init "init"
        :state "state"
        :prefix ~prefix
        :constructors {[] []
                       [~@types] []}
        :methods [~@method-decls])

       (defn ~init-sym
         ([] [[] (~init-sym ~@null-constructor-values)])
         ([~@names]
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
