(ns ^:figwheel-no-load bombinate.styles.app
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
	[:body {:color "#101111"
	        :background-color "#fffff8"}]
	[:a {:color "#229999"
	     :font-weight "normal"
	     :text-decoration "none"}
	  [:&:hover {:text-decoration "underline"
	             :color "#118888"}]])

