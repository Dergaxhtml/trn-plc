(ns torun-places.list-views
 (:require [reagent.core :as r]
   [re-frame.core :as rf]))

(def ReactNative (js/require "react-native"))
(def LayoutAnimation (.-LayoutAnimation ReactNative))
(def ListView (.-ListView ReactNative))

(def view (r/adapt-react-class (.-View ReactNative)))
(def list-view (r/adapt-react-class (.-ListView ReactNative)))
(def text (r/adapt-react-class (.-Text ReactNative)))

(when (= "android" (.. ReactNative -Platform -OS))
  (.. ReactNative -UIManager (setLayoutAnimationEnabledExperimental true)))

(defn item-view [id {:keys [id]}]
  (fn []
      [text "hello"]
      [text {:set-views "hello"}]))

(defn list-views []
      (let [ds (new (.-DataSource ListView) #js {:rowHasChanged not=})]
           (r/create-class
             {:reagent-render
              (fn []
                  (let [dataSource (.cloneWithRows ds (clj->js @(rf/subscribe [:get-places])))]
                       [list-view
                        {:style               {:flex       1
                                               :align-self "stretch"}
                        :dataSource dataSource
                        :renderRow (fn [rowData _ rowID]
                                       (r/as-element
                                       #_ [text "hello"]
                                       [item-view rowID (js->clj rowData
                                                                   :keywordize-keys true)]))
                        :enableEmptySections true}]))
              :componentWillUpdate
              #(.easeInEaseOut LayoutAnimation)})))
