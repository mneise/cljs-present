(ns cljs-present.code
  (:require [cljs.js :as cljs]
            [goog.dom :as dom]
            [goog.object :as obj]
            [goog.events :as events]
            [codemirror :as codemirror]
            [clojure-mod :as clj-mod])
    (:import [goog.ui Button]
             [goog.events EventType]))


;; create cljs.user
(set! (.. js/window -cljs -user) #js {})

;; setup
(def st (cljs/empty-state))

(defn cb [output]
  (fn [{:keys [value error]}]
    (if-not error
      (set! (.-value output) (str value))
      (do
        (set! (.-value output) "ERROR")
        (.error js/console error)))))

(def cm-opts
  #js {:lineNumbers true
       :mode "clojure"})

(defn setup-listeners [slide-count]
  (let [slides (for [idx (range slide-count)]
                 (dom/getElement (str "slide-" idx)))]
    (doseq [slide slides]
      (let [input (dom/getElementByClass "cljs-code" slide)
            run (dom/getElementByClass "cljs-run" slide)
            output (dom/getElementByClass "cljs-result" slide)]
        (when (and input run output)
          (let [cm (.fromTextArea js/codemirror input cm-opts)]
            (.render (Button. "Run") run)
            (events/listen run EventType.CLICK
              (fn [e]
                (cljs/eval-str st (.getValue cm) 'input
                               {:eval cljs/js-eval} (cb output))))))))))

(defn setup [slide-count]
  (setup-listeners slide-count))
