### Initial Commit

bombinate's idea is to fetch starred projects from [Github](https://developer.github.com/v3/activity/starring/) and then ...do something with them, like grouping by various conditions, fetch the data from the API in "background" and other stupid things that I can't think of right now. Would be also nice to have this deployed somewhere at some point.

This is an ongoing experiment to learn the basics of Clojure/ClojureScript by going beyond "Hello world".

On the frontend, the development will be powered by [figwheel](https://github.com/bhauman/lein-figwheel) and [reagent](https://github.com/reagent-project/reagent) will provide a minimalistic ReactJS for ClojureScript. To interact with the Github API, I'm going to use [cljs-ajax](https://github.com/yogthos/cljs-ajax).

#### Bookmarks

Two interesting presentations from Clojure / West 2015.

Bruce Hauman about Developing ClojureScript with Figwheel:

[![Bruce Hauman - Developing ClojureScript With Figwheel](http://img.youtube.com/vi/j-kj2qwJa_E/0.jpg)](https://www.youtube.com/watch?v=j-kj2qwJa_E "Bruce Hauman - Developing ClojureScript With Figwheel")

And this one goes over ReactJS landscape (April 2015) for ClojureScript:

[![Luke VanderHart - The ReactJS Landscape](http://img.youtube.com/vi/oRmj3IUkRVk/0.jpg)](https://www.youtube.com/watch?v=oRmj3IUkRVk "Luke VanderHart - The ReactJS Landscape")

