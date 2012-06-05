(ns fnjs-app.cfg
  (:use [ clojure.java.io :only [ file ] ]) )

; --

(def app-vsn    "v0.0.1")
(def fnjs-vsn   "v0.1.1")

(def copyright  "Copyright (C) 2012  Felix C. Stegerman")
(def fnjs-url   "https://github.com/obfusk/fnjs#readme")

; --

(def ex-dir "resources/public/examples")
(def examples (->> ex-dir file .listFiles (map #(.getName %1)) sort))

(def ugly-cmd
  (clojure.string/split "uglifyjs -b -i 2 -nm -ns" #"\s+") )

; --
