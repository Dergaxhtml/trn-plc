(ns torun-places.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-places
  (fn [db _]
    (:places db)))



(reg-sub
  :get-showing
  (fn [db _]
      (:showing db)))

