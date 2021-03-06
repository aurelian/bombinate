(ns user
  (:require [figwheel-sidecar.config :as config]
            [figwheel-sidecar.system :as sys]
;;            [figwheel-sidecar.repl-api :as ra]
            [figwheel-sidecar.components.file-system-watcher :as fsw]
            [com.stuartsierra.component :as component]
            [garden.core :refer [css]]
            [clojure.pprint :refer [pprint]]))

;; TODO: maybe push garden-watcher to figwheel-sidecar.repl-api/*repl-api-system*

(defn hello-world []
  (println "Hello figwheel world. Project config is:")
  (pprint (config/get-project-config)))

(defn garden-config []
  (:builds (:garden (config/get-project-config))))

(defn garden-watch-paths []
  ((comp vec distinct mapcat) :source-paths (garden-config)))

(defn compile-build [{:keys [id compiler stylesheet]}]
  (println (str "--> building " id "."))
  (css compiler (eval stylesheet)))

(defn garden-watcher [watcher files]
  ;; this doesn't actually do anything with the changed files,
  ;; but a notification that I need to tell garden to compile all the things.
  (doseq [build (garden-config)]
    (compile-build build)))

(def system
  (atom
    (component/system-map
      :figwheel-system (sys/figwheel-system (sys/fetch-config))
      :css-watcher (sys/css-watcher {:watch-paths ["resources/public/css"]})
      :garden-watcher (fsw/file-system-watcher
                       {:watcher-name "Garden"
                        :notification-handler garden-watcher
                        :watch-paths (garden-watch-paths)}))))

(defn start []
  (println "Starting.")
  (swap! system component/start))

(defn stop []
  (println "Stoping.")
  (swap! system component/stop))

(defn cljs-two []
  (println "Starting cljs-two repl. Make sure system is running.")
  (when @system)
  (sys/cljs-repl (:figwheel-system @system) "dev"))

(defn cljs []
  (println "Starting cljs repl. Make sure system is running.")
  (sys/cljs-repl (:figwheel-system @system) "dev"))
