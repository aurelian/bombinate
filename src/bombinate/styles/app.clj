(ns bombinate.styles.app
  (:require [garden.def :refer [defstyles]]
            [garden.units :refer [px percent]]))

(defstyles screen
  [:html {:font-size (px 15)}]
  [:body
   {:width (percent 87.5)
    :color "#111"
    :margin-left "auto"
    :margin-right "auto"}]
  [:h1 {:font-weight 400}]
  [:h2 {:font-weight 400}]
  [:h3 {:font-weight 400}])

