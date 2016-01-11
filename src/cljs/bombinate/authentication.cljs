(ns bombinate.authentication
  (:require [reagent.cookies :as cookies]
            [reagent.session :as session]))

;; https://github.com/djljr/reagent-github-oauth/blob/master/src/clj/reagent_github_oauth/handler.clj

;; https://github.com/JarrodCTaylor/reagent-batteries-included-token-auth/tree/master

;; https://github.com/beetleman/db-laboratory-diary/blob/master/src/cljs/db_laboratory_diary/core.cljs

;; https://github.com/gariepyalex/GLO-3102-cljs/blob/master/src/moviesclj/authentication/authentication.cljs

;; (set! (.-location js/window) "http://yahoo.com")
(defn update-user!
  []
  (let [session-id (cookies/get :y-session)]
    (println (str "session-id:" session-id))))

;;    (when (nil? session-id)
;;      (js/alert (str "here -- " session-id)))))
