(ns bombinate.handler
  (:require [bidi.ring :refer (make-handler)]
            [ring.logger :as logger]
            [ring.util.response :as res]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defn index-handler [request]
  (res/resource-response "index.html" {:root "public"}))

(defn article-handler [{:keys [route-params]}]
  (res/response (str "You are viewing article: " (:id route-params))))

(def app-handler
  (make-handler ["/"
                 {"index.html" index-handler
                  ["articles/" :id "/article.html"] article-handler}]))

(def app
  (-> app-handler
    (logger/wrap-with-logger)
    (wrap-defaults api-defaults)))
