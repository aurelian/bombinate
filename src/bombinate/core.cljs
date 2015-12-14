(ns bombinate.core
  (:require [reagent.core :as r]
            [camel-snake-kebab.core :refer [->kebab-case-keyword]]
            [camel-snake-kebab.extras :refer [transform-keys]]
            [ajax.core :as ajax]))

;; curl -H "Accept: application/vnd.github.v3.star+json" https://api.github.com/users/aurelian/starred > resources/public/starred-page-sample.json

(enable-console-print!)

;; Utilities
(defn lang [lang-group]
  (let [i (key lang-group)]
    (if (nil? i)
      "N/A"
      i)))

(def repo-keys
  [:id
   :name
   :full-name
   :html-url
   :description
   :stargazers-count
   :homepage
   :language
   :created-at
   :updated-at
   :pushed-at])

;; define your app data so that it doesn't get over-written on reload
(def app-state (r/atom {:projects []}))

(defn project-component [project]
  [:li {:id (:id project)}
   [:div
    [:a {:href (:html-url project)} (:full-name project)]
    [:span (str " " (:description project))]]])

(defn projects-component []
  [:div [:h2 "Projects"]
   [:div [:ul
     (for [lang-group (:projects @app-state)]
       ^{:key (lang lang-group)} [:li [:div
                                  [:h3 (lang lang-group)]
                                  [:ul
                                   (for [project (val lang-group)]
                                    ^{:key (:id project)} [project-component project])]]])]]])

(defn mount-components []
  (r/render-component [projects-component]
                      (. js/document (getElementById "app"))))

(defn ajax-handler [json-data]
  (swap! app-state assoc :projects
    (group-by :language
      (map #(conj {:starred-at (:starred-at %1)} (select-keys (:repo %1) repo-keys))
            (transform-keys ->kebab-case-keyword json-data)))))

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
  (init!)
)

