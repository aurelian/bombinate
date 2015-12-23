(ns ^:figwheel-no-load bombinate.styles.app
  (:require [garden.def :refer [defstyles]]
            [garden.units :as units]
            [gardener.resets :as resets]
            [gardener.respond :as respond :refer [breakpoints]]
            [mesh.grid :as grid]))

(def gutter (units/px 20))

(defstyles screen

	resets/reset-common-selectors

	[:html {:font-size (units/px 16)}]
	[:body {:width (units/percent 89)
	        :color "#101111"
					;; :color "red"
	        :background-color "#fffff8"
	        :padding-left (units/px 12.5)
	        :margin-left "auto"
	        :margin-right "auto"}]
	[:h1 :h2 :h3 {:font-weight 400}]
	[:a {:color "#229999"
	     :font-weight "normal"
	     :text-decoration "none"}
	  [:&:hover {:text-decoration "underline"
	             :color "#118888"}]]

	;; https://github.com/facjure/mesh/blob/master/src/mesh/grid.cljc
  
  (grid/create ".grid" gutter)
	(grid/wrap-widths 978)
	;;(grid/create-nested-units)
	;;(grid/nuke-gutters-and-padding)
	;;(grid/respond-small (:mobile breakpoints) gutter)
	;;(grid/respond-medium (:tablet breakpoints))
	;;[:div.grid {:border "1px solid red"}]


  )

