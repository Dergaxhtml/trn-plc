(ns torun-places.list-views
 (:require [reagent.core :as r]
   [re-frame.core :as rf]))

(def ReactNative (js/require "react-native"))
(def FontAwesome (js/require "react-native-vector-icons/FontAwesome"))
(def MaterialIcons (js/require "react-native-vector-icons/MaterialIcons"))
(def LayoutAnimation (.-LayoutAnimation ReactNative))
(def ListView (.-ListView ReactNative))

(def view (r/adapt-react-class (.-View ReactNative)))
(def list-view (r/adapt-react-class (.-ListView ReactNative)))
(def fontawesome-icon (r/adapt-react-class (.-default FontAwesome)))
(def material-icon (r/adapt-react-class (.-default MaterialIcons)))

(when (= "android" (.. ReactNative -Platform -OS))
  (.. ReactNative -UIManager (setLayoutAnimationEnabledExperimental true)))

(defn item-view [id {:keys [id]}]
  (fn []
      [text {:set-views "hello"}]
      [text {:set-views "hello"}]))

(defn list-views []
      (let [ds (new (.-DataSource ListView) #js {:rowHasChanged not=})]
           (r/create-class
             {:reagent-render
              (fn []
                  (let [dataSource (.cloneWithRows ds (clj->js @(rf/subscribe [:get-views])))]
                       [list-view
                        {:style               {:flex       1
                                               :align-self "stretch"}
                        :dataSource dataSource
                        :renderRow (fn [rowData _ rowID]
                                       (r/as-element
                                         [item-view rowID (js->clj rowData
                                                                   :keywordize-keys true)]))
                        :enableEmptySections true}]))
              :componentWillUpdate
              #(.easeInEaseOut LayoutAnimation)})))
