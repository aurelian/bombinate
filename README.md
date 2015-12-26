## bombinate

bom•bi•nate (bŏmˈbĭ-nātˌ)
  v. To buzz; hum

bombinate was word of the day on 7th of Dec on http://dictionary.reference.com

This project is a playground where I attempt to improve my skills with Clojure/ClojureScript using experimental learing.

There are some [notes](/notes) on the process.

### Stuff to be done (updated 26.Dec 2015)

 * note about vim
 * write short note about garden / figwheel integration
 * compile .md notes to resources/public/notes/.html
 * deploy somewhere (gh-pages?)
 * [secretary](https://github.com/gf3/secretary) or other routing lib to get some routes going
 * [github](https://developer.github.com/v3/oauth/) oauth to log me in
 * fetch all stars from the api grouped by language

### Setup

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL.

To get a nREPL *and* start the interactive development:

    rlwrap lein repl

    (start)
    (cljs)
    (in-ns 'bombinate.core)
    ... do stuff

Start Garden auto watch and figwheel inside nREPL:

    rlwrap lein repl

    (require '[playground :as p] :reload)
    (p/start)

### License

Copyright © 2015 aurelian

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
