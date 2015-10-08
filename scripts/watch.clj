(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'cljs-present.core
   :output-to "resources/public/js/out/cljs_present.js"
   :output-dir "resources/public/js/out"
   :asset-path "js/out"
   :pretty-print true
   :verbose true
   :foreign-libs [{:file "resources/public/js/libs/codemirror-5.5/lib/codemirror.js"
                   :provides ["codemirror"]
                   :module-type :commonjs}
                  {:file "resources/public/js/libs/codemirror-5.5/mode/clojure/clojure.js"
                   :provides ["clojure-mod"]
                   :module-type :commonjs}
                  {:file "resources/public/js/libs/hammer.js-2.0.4/hammer.js"
                   :provides ["hammer"]
                   :module-type :commonjs}]})
