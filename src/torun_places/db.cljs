(ns torun-places.db
  (:require [clojure.spec.alpha :as s]))



;; spec of app-db
(s/def ::id string?)
(s/def ::desc string?)
(s/def ::done? boolean?)

(s/def ::view
  (s/keys :req-un [::desc ::done?]))

(s/def ::view (s/map-of ::id ::view :min-count 0))

(s/def ::showing #{:all :active :completed})

(s/def ::app-db
  (s/keys :req-un [ ::showing]))

;; initial state of app-db
(def app-db {:places ["Port Drzewny" "Kadr"]
              :showing :all})