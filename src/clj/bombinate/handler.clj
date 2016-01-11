(ns bombinate.handler
  (:require [bidi.ring :refer (make-handler)]
            [ring.util.response :as res]))

(defn index-handler
  [request]
  (res/response "Hello Index"))

(defn article-handler
  [{:keys [route-params]}]
  (res/response (str "You are viewing article: " (:id route-params))))

(def handler
  (make-handler ["/"
                 {"index.html" index-handler
                  ["articles/" :id "/article.html"] article-handler}]))

(def app
  (-> handler))
