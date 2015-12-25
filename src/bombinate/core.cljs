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
  [:div {:class "col s4" :id (:id project)}
   [:div {:class "card light-green lighten-5"}
    [:div {:class "card-content grey-text text-darken-4"}
     [:span {:class "card-title"} [:a {:href (:html-url project)} (:full-name project)]]
     [:p (str " " (:description project))]]
    [:div {:class "card-action"} "Hello"]]])

(defn projects-component []
  [:div {:class "container"}
   [:h3 "Projects"]
   [:div {:class "row"}
     (for [lang-group (:projects @app-state)]
       ^{:key (lang lang-group)} [:div {:class "col s12"}
                                  [:h4 (lang lang-group)]
                                  (map (fn[projects-group]
                                         [:div {:class "row"}
                                          (for [project projects-group]
                                            ^{:key (:id project)} [project-component project])])
                                       (partition 3 3 [] (val lang-group)))])]])

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

