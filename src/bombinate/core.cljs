(ns bombinate.core
  (:require [reagent.core :as r]
            [camel-snake-kebab.core :refer [->kebab-case-keyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [ajax.core :as ajax]))

;; curl -H "Accept: application/vnd.github.v3.star+json" https://api.github.com/users/aurelian/starred > resources/public/starred-page-sample.json

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload
(def app-state (r/atom {:projects []}))

(defn project-component [{:keys [starred-at repo] :as project}]
  [:li {:id (str "repo-" (:id repo))}
   [:div
    [:span (str starred-at "/ ")]
    [:a {:href (:html-url repo)} (:full-name repo)]
    [:span (str " [" (:language repo) "]=> " (:description repo))]]])

(defn projects-component []
  [:div
   [:h1 "Projects"]
   [:div
    [:ul
     (for [project (:projects @app-state)]
       ^{:key (:id (:repo project))} [project-component project])]]])

(defn mount-components []
  (r/render-component [projects-component]
                      (. js/document (getElementById "app"))))

;;
;; starred_at
;; repo
;;   id
;;   name
;;
;; starred_at
;; repo
;;   id
;;   name
;;
(defn ajax-handler [json-data]
  (swap! app-state assoc :projects (transform-keys ->kebab-case-keyword json-data)))

(defn fetch-starred-projects! [url]
  (ajax/GET url
            {:handler ajax-handler
             :error-handler (fn [details] (.warn js/console (str "Failed to load data: " details "!")))
             :response-format :json
             :keywords? true }))

(defn init! []
  (println "[init] called")
  (fetch-starred-projects! "/starred-page-sample.json")
  (mount-components))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  (init!)
)

