(ns bombinate.handler
  (:require [bidi.ring :as br]
            [ring.logger :as logger]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(def routes
  ["/"  [[""   (br/redirect "/index.html")]
         [""   (br/resources-maybe {:prefix ""})]
         [true (fn [req] {:status  404
                          :headers {"Content-Type" "text/plain; charset=UTF-8"}
                          :body    "Not to be Found"})]]])

(def app
  (-> (br/make-handler routes)
      (logger/wrap-with-logger)
      (wrap-defaults api-defaults)))
