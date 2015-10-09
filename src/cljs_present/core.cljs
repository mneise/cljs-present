(ns cljs-present.core
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [clojure.string :as str]
            [cljs-present.code :as code]
            [hammer :as hammer])
  (:import [goog.events KeyHandler]))

(enable-console-print!)

(def idx (atom 0))

(defn change-slide [slide-count direction]
  (let [active (.-activeElement js/document)
        slide (dom/getElement (str "slide-" @idx))
        input (dom/getElementByClass "CodeMirror" slide)]
    (when (or (not (and active input))
              (not (dom/contains input active)))
      (let [diff (case direction
                   :left -1
                   :right 1
                   0)
            next-idx (mod (+ @idx diff) slide-count)
            next (dom/getElement (str "slide-" next-idx))]
        (dom/setProperties slide #js {:class "hidden slide"})
        (dom/setProperties next #js {:class "current slide"})
        (reset! idx next-idx)))))

(defn keydown [slide-count]
  (fn [e]
    (let [direction (case (.-keyCode e)
                      37 :left
                      39 :right
                      0)]
      (change-slide slide-count direction))))

(defn swiperight [slide-count]
  (fn [e]
    (change-slide slide-count :right)))

(defn swipeleft [slide-count]
  (fn [e]
    (change-slide slide-count :left)))

(defn hide-all-slides [count]
  (doseq [i (range count)]
    (let [slide (dom/getElement (str "slide-" i))]
      (dom/setProperties slide #js {:class "hidden slide"}))))

(defn setup-listeners [slide-count]
  (let [doc-kh (KeyHandler. js/document)
        h (js/hammer. (dom/getElement "slides"))]
    (events/listen doc-kh "key" (keydown slide-count))
    (.on h "swiperight" (swiperight slide-count))
    (.on h "swipeleft" (swipeleft slide-count))))

(defn load-slides []
  (let [slides (dom/getElementsByClass "slide")
        keys (.keys js/Object slides)
        slide-count (count keys)]
    (doseq [k keys]
      (dom/setProperties (aget slides k) #js {:id (str "slide-" k)}))
    (code/setup slide-count)
    (hide-all-slides slide-count)
    (setup-listeners slide-count)
    (dom/setProperties (dom/getElement "slide-0") #js {:class "current slide"})))

(load-slides)
