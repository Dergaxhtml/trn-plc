(ns torun-places.android.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [subscribe dispatch dispatch-sync]]
            [torun-places.events]
            [torun-places.subs]))

(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))


;(def list-view (r/adapt-react-class (.-ListView ReactNative)))


(def logo-img (js/require "./images/cljs.png"))

(defn alert [title]
      (.alert (.-Alert ReactNative) title))

(defn app-root []
      (fn []
       [text "Elo"]))




(defn init []
      (dispatch-sync [:initialize-db])
      (.registerComponent app-registry "TorunPlaces" #(r/reactify-component app-root)))


;(.registerComponent ui/app-registry "auricle" #(r/reactify-component app-root)))
