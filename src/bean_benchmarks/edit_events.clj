(ns bean-benchmarks.edit-events
  (:require [clojure.spec :as s]
            [clojure.spec.gen :as spec.gen]
            [clojure.future :refer :all]))

(s/def ::timestamp (s/with-gen (s/int-in 1421280000 4135190400)
                     #(spec.gen/choose 1421280000 1482870611)))
(s/def ::channel (s/with-gen string? #(s/gen #{"#en.wikipedia"})))
(s/def ::title (s/with-gen string? spec.gen/string-alphanumeric))
(s/def ::diff-url (s/with-gen string? spec.gen/string-alphanumeric))
(s/def ::user string?)
(s/def ::byte-diff (s/with-gen int? #(spec.gen/choose -1000 1000)))
(s/def ::summary string?)
(s/def ::is-minor boolean?)
(s/def ::is-new boolean?)
(s/def ::is-unpatrolled boolean?)
(s/def ::is-bot-edit boolean?)
(s/def ::is-special boolean?)
(s/def ::is-talk boolean?)

(s/def ::edit-event (s/keys :req [::timestamp
                                  ::channel
                                  ::title
                                  ::diff-url
                                  ::user
                                  ::byte-diff
                                  ::summary
                                  ::is-minor
                                  ::is-new
                                  ::is-unpatrolled
                                  ::is-bot-edit
                                  ::is-special
                                  ::is-talk]))

(defn generate-test-samples [n-samples]
  (spec.gen/sample (s/gen ::edit-event) n-samples))
