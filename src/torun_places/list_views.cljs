(ns torun-places.list-views
 (:require [reagent.core :as r]
   [re-frame.core :as rf]))

(def ReactNative (js/require "react-native"))
(def LayoutAnimation (.-LayoutAnimation ReactNative))
(def ListView (.-ListView ReactNative))

(def view (r/adapt-react-class (.-View ReactNative)))
(def list-view (r/adapt-react-class (.-ListView ReactNative)))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def touchable (r/adapt-react-class (.-TouchableNativeFeedback ReactNative)))


(when (= "android" (.. ReactNative -Platform -OS))
  (.. ReactNative -UIManager (setLayoutAnimationEnabledExperimental true)))

(defn item-view [place]
  [touchable {:style {:background-color "#999" :padding 10 :border-radius 5}
                        :on-press #(println place)}
   [text place]])

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
                                        [item-view  rowData (js->clj rowData
                                                                   :keywordize-keys true)]))
                        :enableEmptySections true}]))
              :componentWillUpdate
              #(.easeInEaseOut LayoutAnimation)})))
