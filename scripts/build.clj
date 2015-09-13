(require '[cljs.build.api :as b])

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "src"
    {:main 'cljs-present.core
     :output-to "out/cljs_present.js"
     :output-dir "out"
     :verbose true
     :optimizations :whitespace
     :pretty-print true
     :foreign-libs [{:file "libs/codemirror-5.5/lib/codemirror.js"
                     :provides ["codemirror"]
                     :module-type :commonjs}
                    {:file "libs/codemirror-5.5/mode/clojure/clojure.js"
                     :provides ["clojure-mod"]
                     :module-type :commonjs}]})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))

;; (let [start (System/nanoTime)]
;;   (b/build "src"
;;     {:main 'cljs-present.core
;;      :output-to "resources/public/js/out/reveal_cljs.js"
;;      :output-dir "resources/public/js/out"
;;      :asset-path "js/out"
;;      :pretty-print true
;;      :verbose true})
;;   (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))
