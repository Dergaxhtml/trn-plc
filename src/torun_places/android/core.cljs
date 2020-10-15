(ns torun-places.android.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [dispatch dispatch-sync]]
            [torun-places.events]
            [torun-places.root :refer [root]]
            [torun-places.subs]))



(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))


(defn alert [title]
      (.alert (.-Alert ReactNative) title))


(defn app-root []
      [root])

 (defn init []
       (dispatch-sync [:initialize-db])
       (.registerComponent app-registry "TorunPlaces" #(r/reactify-component app-root)))




