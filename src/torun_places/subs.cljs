(ns torun-places.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :get-views
  (fn [db _]
    (:views db)))

(reg-sub
  :get-showing-todos
  (fn [{:keys [views showing]} _]
      (filter-shown-todos views showing)))

(reg-sub
  :get-showing
  (fn [db _]
      (:showing db)))

(reg-sub
  :get-showing
  (fn [db _]
      (:showing db)))