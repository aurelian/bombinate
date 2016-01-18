(defproject bombinate "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring-logger "0.7.5"]
                 [ring/ring-defaults "0.1.5"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374"]
                 [reagent "0.5.1"]
                 [reagent-utils "0.1.7"]
                 [bidi "1.25.0"]
                 [kibu/pushy "0.3.6"]
                 [garden "1.3.0"]
                 [cljs-ajax "0.5.2"]
                 [camel-snake-kebab "0.3.2"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-ring "0.9.7"]
            [lein-garden "0.2.6"]
            [lein-figwheel "0.5.0-3"]]

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.10"]
                                  [figwheel-sidecar "0.5.0-1"]]
                   :source-paths ["src/clj", "src/cljs", "dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

  :source-paths ["src/clj", "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "resources/public/css/style.css"]

  :garden {:builds [{;; Optional name of the build:
                     :id "dev"
                     ;; Source paths where the stylesheet source code is
                     :source-paths ["src/bombinate/styles"]
                     ;; The var containing your stylesheet:
                     :stylesheet bombinate.styles.app/screen
                     ;; Compiler flags passed to `garden.core/css`:
                     :compiler {;; Where to save the file:
                                :output-to "resources/public/css/style.css"
                                ;; Compress the output?
                                :pretty-print? false}}]}

  :ring {:handler bombinate.handler/app
         :port 4898
         :auto-refresh? true
         :nrepl {:start? true}
         :uberwar-name "bombinate.war"}

  :uberjar-name "bombinate.jar"

  :main bombinate.server

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src/cljs"]

                :figwheel {:on-jsload "bombinate.core/on-js-reload"}

                :compiler {:main bombinate.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/bombinate.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}
               ;; This next build is an compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once min
               {:id "min"
                :source-paths ["src/cljs"]
                :compiler {:output-to "resources/public/js/compiled/bombinate.js"
                           :main bombinate.core
                           :optimizations :whitespace
                           :pretty-print true}}]}

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             :server-port 4898 ;; default
             ;; :server-ip "127.0.0.1"
             :css-dirs ["resources/public/css"]}) ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             ;; doesn't work for you just run your own server :)
             ;; :ring-handler bombinate.handler/app}) ;; <-- figwheel can't find this

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"