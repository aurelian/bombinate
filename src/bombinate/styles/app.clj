(ns bombinate.styles.app
  (:require [garden.def :refer [defstyles]]
            [garden.units :refer [px]]))

(defstyles screen
  [:body
   {:font-size (px 16)
    :line-height 1.5}])
