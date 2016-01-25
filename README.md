## bombinate

bom•bi•nate (bŏmˈbĭ-nātˌ)
  v. To buzz; hum

bombinate was word of the day on 7th of Dec on http://dictionary.reference.com

This project is a playground where I attempt to improve my skills with Clojure/ClojureScript using experimental learing.

There are some [notes](/notes) on the process.

### Stuff to be done (updated 20.Jan 2016)

 * implement [workflow](#workflow)
 * note about vim
 * write short note about garden / figwheel integration
 * [github](https://developer.github.com/v3/oauth/) oauth to log me in
 * deploy to heroku (alternatives?)
 * note about lein/boot
 * compile .md notes to resources/public/notes/.html

### Workflow

 1. user goes to home page.
 2. show not authenticated message and option to click to redirect to github oauth.
 3. performs authentication on github.
 4. redirect to app.
 5. fetch stars.
 6. ?!?
 7. Rapture / Profit $$$
 8. logout.

### Setup

To start the app in development environment run:

```
boot dev
```

In another terminal, connect to repl:

```
boot repl -c
```

### License

Copyright © 2015,2016 aurelian oancea

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
