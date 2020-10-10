(ns torun_places.ui
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [cljs-time.format :as tformat]
            [cljs-time.coerce :as tcoerce]
            [torun_places.events]
            [torun_places.subs]))

(def ReactNative (js/require "react-native"))

(def list-view (r/adapt-react-class (.-ListView ReactNative)))
(def list-view-ds (.-DataSource (.-ListView ReactNative)))

(defn create-ds [change-fn] (list-view-ds. #js{:rowHasChanged change-fn}))