(require '[cljs.build.api :as b])

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "src"
    {:main 'cljs-present.core
     :output-to "resources/public/js/release/cljs_present.js"
     :output-dir "resources/public/js/release"
     :asset-path "js/release"
     :verbose true
     :pretty-print true
     :optimizations :whitespace
     :foreign-libs [{:file "resources/public/js/libs/codemirror-5.5/lib/codemirror.js"
                     :provides ["codemirror"]
                     :module-type :commonjs}
                    {:file "resources/public/js/libs/codemirror-5.5/mode/clojure/clojure.js"
                     :provides ["clojure-mod"]
                     :module-type :commonjs}
                    {:file "resources/public/js/libs/hammer.js-2.0.4/hammer.js"
                     :provides ["hammer"]
                     :module-type :commonjs}]})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))
