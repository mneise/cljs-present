(ns cljs-present.core
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [clojure.string :as str]
            [cljs-present.code :as code])
  (:import [goog.events KeyHandler]))

(enable-console-print!)

(def idx (atom 0))

(defn next-slide [slide-count]
  (fn [e]
    (let [active (.-activeElement js/document)
          slide (dom/getElement (str "slide-" @idx))
          input (dom/getElementByClass "CodeMirror" slide)]
      (when (or (not (and active input))
                (not (dom/contains input active)))
        (let [diff (case (.-keyCode e)
                     37 -1
                     39 1
                     0)
              next-idx (mod (+ @idx diff) slide-count)
              next (dom/getElement (str "slide-" next-idx))]
          (dom/setProperties slide #js {:class "hidden slide"})
          (dom/setProperties next #js {:class "current slide"})
          (reset! idx next-idx))))))

(defn hide-all-slides [count]
  (doseq [i (range count)]
    (let [slide (dom/getElement (str "slide-" i))]
      (dom/setProperties slide #js {:class "hidden slide"}))))

(defn setup-listener [slide-count]
  (let [doc-kh (KeyHandler. js/document)]
    (events/listen doc-kh "key" (next-slide slide-count))))

(defn load-slides []
  (let [slides (dom/getElementsByClass "slide")
        keys (.keys js/Object slides)]
    (doseq [k keys]
      (dom/setProperties (aget slides k) #js {:id (str "slide-" k)}))
    (code/setup (count keys))
    (hide-all-slides (count keys))
    (setup-listener (count keys))
    (dom/setProperties (dom/getElement "slide-0") #js {:class "current slide"})))

(load-slides)
