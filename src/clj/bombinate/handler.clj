(ns bombinate.handler
  (:require [bidi.ring :as br]
            [ring.logger :as logger]
            [ring.util.response :as res]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defn index-handler [request]
  (res/resource-response "index.html"))

(defn article-handler [{:keys [route-params]}]
  (res/response (str "You are viewing article: " (:id route-params))))

(def app-handler
  (br/make-handler
    [
      "/"
                 {"index.html" index-handler
                  ["articles/" :id ".html"] article-handler}
      (br/resources {:prefix "public"})
     ]

                ))

(def app
  (-> app-handler
    (logger/wrap-with-logger)
    (wrap-defaults api-defaults)))
