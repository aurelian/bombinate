(ns user
  (:use [figwheel-sidecar.repl-api :as ra]))

;; (garden.core/css {:output-to "foo.css" :pretty-print? false} bombinate.styles.app/screen)

(defn start [] (ra/start-figwheel!))

(defn stop [] (ra/stop-figwheel!))

(defn cljs [] (ra/cljs-repl "dev"))
