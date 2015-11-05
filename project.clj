(defproject cljs-present "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.166"]]
  :jvm-opts ^:replace ["-Xmx1g" "-server"]
  :plugins [[lein-figwheel "0.4.1"]
            [lein-cljsbuild "1.1.1-SNAPSHOT"]]
  :source-paths ["src" "target/classes"]
  :clean-targets ["out" "release"]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :compiler {:main cljs-present.core
                                   :output-to "resources/public/js/out/cljs_present.js"
                                   :output-dir "resources/public/js/out"
                                   :source-map "resources/public/js/out/map.js.map"
                                   :asset-path "js/out"
                                   :verbose true
                                   :pretty-print true
                                   :foreign-libs [{:file "resources/public/js/libs/codemirror-5.5/lib/codemirror.js"
                                                   :provides ["codemirror"]
                                                   :module-type :commonjs}
                                                  {:file "resources/public/js/libs/codemirror-5.5/mode/clojure/clojure.js"
                                                   :provides ["clojure-mod"]
                                                   :module-type :commonjs}
                                                  {:file "resources/public/js/libs/hammer.js-2.0.4/hammer.js"
                                                   :provides ["hammer"]
                                                   :module-type :commonjs}]}}
                       {:id "min"
                        :source-paths ["src"]
                        :compiler {:main cljs-present.core
                                   :output-to "resources/public/js/release/cljs_present.js"
                                   :output-dir "resources/public/js/release"
                                   :source-map "resources/public/js/release/map.js.map"
                                   :asset-path "js/release"
                                   :optimizations :whitespace
                                   :foreign-libs [{:file "resources/public/js/libs/codemirror-5.5/lib/codemirror.js"
                                                   :provides ["codemirror"]
                                                   :module-type :commonjs}
                                                  {:file "resources/public/js/libs/codemirror-5.5/mode/clojure/clojure.js"
                                                   :provides ["clojure-mod"]
                                                   :module-type :commonjs}
                                                  {:file "resources/public/js/libs/hammer.js-2.0.4/hammer.js"
                                                   :provides ["hammer"]
                                                   :module-type :commonjs}]}}]}
  :figwheel {:css-dirs ["resources/public/css"]}
  :target-path "target")
