### Initial Commit

bombinate's idea is to fetch starred projects from [Github](https://developer.github.com/v3/activity/starring/) and then ...do something with them, like grouping by various conditions, fetch the data from the API in "background" and other stupid things that I can't think of right now. Would be also nice to have this deployed somewhere at some point.

This is an ongoing experiment to learn the basics of Clojure/ClojureScript by going beyond "Hello world".

On the frontend, the development will be powered by [figwheel](https://github.com/bhauman/lein-figwheel) and [reagent](https://github.com/reagent-project/reagent) will provide a minimalistic ReactJS for ClojureScript. To interact with the Github API, I'm going to use [cljs-ajax](https://github.com/yogthos/cljs-ajax).

#### Bookmarks

Two interesting presentations from Clojure / West 2015.

Bruce Hauman demonstrates main features of Figwheel developing ClojureScript applications:

[![Bruce Hauman - Developing ClojureScript With Figwheel](http://img.youtube.com/vi/j-kj2qwJa_E/0.jpg)](https://www.youtube.com/watch?v=j-kj2qwJa_E "Bruce Hauman - Developing ClojureScript With Figwheel")

And BTW this guy lives in a literal bubble (8:00-10:00). Another cool part of the talk is broadcasting the code to various connected devices (21:00), this slides into another demo of the favicon changing its color on a test build. Very nice.

This other talk here goes over ReactJS landscape (as of April 2015) for ClojureScript:

[![Luke VanderHart - The ReactJS Landscape](http://img.youtube.com/vi/oRmj3IUkRVk/0.jpg)](https://www.youtube.com/watch?v=oRmj3IUkRVk "Luke VanderHart - The ReactJS Landscape")

It basically helps you to decide which library to use going over the features of Om (15:00), Reagent (22:30) or Quiescent (30:00).

A major difference between those libraries is the way the application (or individual component) state is managed in each. _Om_ has cursors which arbitrate application state. _Reagent_ uses reagent atoms (custom atom type) and basically nothing stops you to have an atom per component if that's what you want. Dereferencing an atom in a component will create a reactive bound between them. _Quiescent_ on the other hand doesn't have component state and it just turns your data into components (no step of transforming the state into data as in Reagent or Om). NO STATE FOR YOU. It works on values and it doesn't care how you got them or what do you use to handle them.

The other difference is how you render your components and here _Quiescent_ takes again a different approach and lets you handle the application render process.
