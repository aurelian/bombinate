(ns ^:figwheel-no-load bombinate.styles.app
  (:require [garden.def :refer [defstyles]]
            [garden.units :as units]))

(defstyles screen
  [:html {:font-size (units/px 15)}]
  [:body
   {:width (units/percent 87.5)
    :color "#111"
    :background-color "#fffff8"
    :padding-left (units/px 12.5)
    :margin-left "auto"
    :margin-right "auto"}]
  [:h1 {:font-weight 400
        :margin-top (units/rem 4)}]
  [:h2 {:font-weight 400}]
  [:h3 {:font-weight 400}])

