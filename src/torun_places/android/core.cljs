(ns torun-places.android.core
  (:require [reagent.core :as r :refer [atom]]
            [re-frame.core :refer [dispatch dispatch-sync]]
            [torun-places.events refer [load-views]]
            [torun-places.root]
            [torun-places.subs]))



(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))


(defn alert [title]
      (.alert (.-Alert ReactNative) title))


(defn app-root []
      [root])

 (defn init []
       (dispatch-sync [:initialize-db])
       (load-views
         (fn [{:keys [views showing]}]
             (dispatch [:load-views views])
             (dispatch [:set-showing showing])))
       (.registerComponent app-registry "TorunPlaces" #(r/reactify-component app-root)))




;(.registerComponent ui/app-registry "auricle" #(r/reactify-component app-root)))
