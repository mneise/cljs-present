(ns cljs-present.macros
  (:require [clojure.pprint :as pprint])
  (:import java.io.File))

(defmacro load-slides [relative-uri]
  "Reads and returns slides as a string."
  (slurp relative-uri))
