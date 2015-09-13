(ns cljs-present.core
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [goog.net.XhrIo :as xhr]
            [markdown.core :refer [md->html]]
            [clojure.string :as str]
            [cljs-present.code :as code])
  (:require-macros [cljs-present.macros :as macros])
  (:import [goog.events KeyHandler]))


(enable-console-print!)

(def idx (atom 0))

(defn slide-pre [idx]
  (str "<div id=\"slide-" idx "\" class=\"slide\"><div class=\"content\">"))

(defn slide-post []
  "</div></div>")

(defn slides->html [slides]
  (let [html (map-indexed (fn [idx s] (str (slide-pre idx)
                                           (md->html s)
                                           (slide-post))) slides)]
    (str/join html)))

(defn next-slide [slide-count]
  (fn [e]
    (let [diff (case (.-keyCode e)
                 37 -1
                 39 1
                 0)
          next-idx (mod (+ @idx diff) slide-count)
          slide (dom/getElement (str "slide-" @idx))
          next (dom/getElement (str "slide-" next-idx))]
      (dom/setProperties slide #js {:class "hidden slide"})
      (dom/setProperties next #js {:class "current slide"})
      (reset! idx next-idx))))

(defn hide-all-slides [count]
  (doseq [i (range count)]
    (let [slide (dom/getElement (str "slide-" i))]
      (dom/setProperties slide #js {:class "hidden slide"}))))

(defn setup-listener [slide-count]
  (let [doc-kh (KeyHandler. js/document)]
    (events/listen doc-kh "key" (next-slide slide-count))))

(defn create-slides [slides]
  (let [slides (str/split slides #"%")
        html (slides->html slides)
        body (.-body js/document)]
    (.insertAdjacentHTML body "afterbegin" html)
    (code/setup 4)
    (hide-all-slides (count slides))
    (setup-listener (count slides))
    (dom/setProperties (dom/getElement "slide-0") #js {:class "current slide"})))

(defn load-slides [uri]
  (xhr/send uri (fn [e]
                  (let [result (-> e .-target .getResponseText)]
                    (create-slides result)))))

(load-slides "slides.md")
