(set-env!
  :source-paths #{"src/cljs" "src/clj"}
  :resource-paths #{"resources/public"}
  :dependencies '[[org.clojure/clojure "1.7.0"]
                  [org.clojure/clojurescript "1.7.170"]
                  [org.clojure/core.async "0.2.374"]
                  [reagent "0.5.1"]
                  [reagent-utils "0.1.7"]
                  [bidi "1.25.0"]
                  [kibu/pushy "0.3.6"]
                  [garden "1.3.0"]
                  [cljs-ajax "0.5.2"]
                  [camel-snake-kebab "0.3.2"]
                  [adzerk/boot-cljs "1.7.170-3"]
                  [weasel "0.7.0" :scope "test"]
                  [pandeiro/boot-http "0.7.0"]
                  [ring-logger "0.7.5"]
                  [ring/ring-defaults "0.1.5"]
                  [adzerk/boot-reload "0.4.2"]
                  [adzerk/boot-cljs-repl "0.3.0"]
                  [com.cemerick/piggieback "0.2.1"]
                  [org.clojure/tools.nrepl "0.2.12"]]
  )

(require '[adzerk.boot-cljs :refer [cljs]]
         '[pandeiro.boot-http :refer [serve]]
         '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])

(deftask dev
  "Launch Immediate Feedback Development Environment"
  []
  (comp
    (serve :handler 'bombinate.handler/app
           :resource-root "target"
           :reload true)
    (watch)
    (reload :on-jsload 'bombinate.core/on-js-reload)
    (cljs-repl) ;; before cljs task
    (cljs)
    (target :dir #{"target"})))
