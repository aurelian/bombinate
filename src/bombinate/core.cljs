(ns bombinate.core
  (:require [reagent.core :as r]
            [ajax.core :as ajax]))

;; curl -H "Accept: application/vnd.github.v3.star+json" https://api.github.com/users/aurelian/starred > resources/public/starred-page-sample.json

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload
(def app-state (r/atom {:projects []}))

(defn project-component [{:keys [repo] :as project}]
  [:li {:id (str "repo-" (:id repo))}
   [:div
    [:a {:href (:html_url repo)} (:full_name repo)]
    [:span (str " - " (:description repo))]]])

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

(defn fetch-starred-projects! []
  (ajax/GET "/starred-page-sample.json"
            {:handler (fn [data] (swap! app-state assoc :projects data))
             :error-handler (fn [details] (.warn js/console (str "Failed to load data: " details "!")))
             :response-format :json
             :keywords? true }))

(defn init! []
  (println "[init] called")
  (fetch-starred-projects!)
  (mount-components))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  (init!)
)

