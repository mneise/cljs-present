# Intro to ClojureScript

@maria__geller

%

* Personal background
* Rationale
* ClojureScript Syntax
* Tooling
* Demos
* Outro

%

![LiveOps](images/liveops.png)

<!-- Clojure/ClojureScript developer at LiveOps -->

%

![OMSCS](images/omscs.png)

<!-- Online MSc student at Georgia Tech -->

%

![GSoC](images/gsoc.jpg)
<!-- GSoC student working on the ClojureScript compiler -->
<!-- GSoC: program to introduce students to open source orgainzations -->

%

![cljs](images/cljs.svg)

%

> Clojure rocks and JavaScript reaches.

*Rich Hickey, NYC Clojure meet up 20 July 2011*

<!-- ClojureScript was introduced in 2011 -->
<!-- compiles Clojure to JavaScript -->

%

### What is Clojure

* introduced in 2007 by Rich Hickey
* Lisp dialect
* runs on the JVM

%

### Why Clojure rocks

* immutable data structure
* support for concurrency
* macros
* great Java interop

<!-- Clojure: introduced in 2007, Lisp dialect, runs on the JVM -->
<!-- immutable data structures, macros,  -->

%

### What is ClojureScript?

* compiles Clojure to JavaScript
* outputs ES3 compatible JavaScript
* compiler written in Clojure
* integrates well with Google Closure

%

### Why ClojureScript?

* more robust code
** ECMAScript 3, immutable data structures, advanced optimization
* same language for back-end and front-end
** share code
* great JavaScript interop
** use existing libraries, JS module support

%

# Finally show me some ClojureScript!

%

### Data Structures

<textarea class="cljs-code">
(def x [2 3 4])
(conj x 1)
[x]
</textarea>
<div class="cljs-run"></div>
<textarea class="cljs-result"></textarea>

### Equality

<textarea class="cljs-code">
(= "2" 2)
</textarea>
<div class="cljs-run"></div>
<textarea class="cljs-result"></textarea>

%

### Functions

<textarea class="cljs-code">
(defn my-add [x y] (+ x y))
(my-add 3 4)
</textarea>
<div class="cljs-run"></div>
<textarea class="cljs-result"></textarea>

<!-- equality on numbers has javascript (not clojure's) semantic -->

%

### Higher-order Functions

<textarea class="cljs-code">
(map inc [1 2 3])
</textarea>
<div class="cljs-run"></div>
<textarea class="cljs-result"></textarea>

%

### JS interop - Property access

<textarea class="cljs-code">
(set! (.. js/document -body -style -backgroundColor) "lightgray")
</textarea>
<div class="cljs-run"></div>
<textarea class="cljs-result"></textarea>

%

### JS interop - Function invocation

<textarea class="cljs-code">
(.alter js/window "Hello, AkJS")
</textarea>
<div class="cljs-run"></div>
<textarea class="cljs-result"></textarea>

%

### Tooling

* source map support
* REPLs
* plugins for live code reloading
* bootstrapped ClojureScript

%

### Demos

%

### In summary

* more robust and simpler code
* advanced optimization
* 

%

# More info

* [ClojureScript GitHub Wiki](https://github.com/clojure/clojurescript/wiki)
* [ClojureScript Koans](http://clojurescriptkoans.com/)
* [ClojureScript Unraveled](https://funcool.github.io/clojurescript-unraveled/)
* [clojurians Slack](http://clojurians.net/)
* [LispCast Video Tutorials](http://www.purelyfunctional.tv/single-page-applications)
