(ns playground
  (:require [figwheel-sidecar.config :as config]
            [figwheel-sidecar.system :as sys]
            [figwheel-sidecar.components.file-system-watcher :as fsw]
            [com.stuartsierra.component :as component]
;;            [garden.core :refer [css]]
            [clojure.pprint :refer [pprint]]
  ))

(defn hello-world []
  (println "Hello figwheel world. Project config is:")
  (pprint (config/get-project-config)))

(defn garden-config []
  (first (:builds (:garden (config/get-project-config)))))

(defn garden-watcher [watcher files]
  (pprint (first files))
  (pprint (slurp (first files)))
  ;; TODO -- file to be eval-in-project
;;  (css (:compiler (garden-config)) (slurp (first files)))
)

(def system
  (atom
    (component/system-map
      :figwheel-system (sys/figwheel-system (sys/fetch-config))
      :css-watcher (sys/css-watcher {:watch-paths ["resources/public/css"]})
      :garden-watcher (fsw/file-system-watcher
         {:watcher-name "Garden"
          :notification-handler garden-watcher
          :watch-paths (:source-paths (garden-config))}))))

(defn start []
  (println "Starting.")
  (swap! system component/start))

(defn stop []
  (println "Stoping.")
  (swap! system component/stop))

