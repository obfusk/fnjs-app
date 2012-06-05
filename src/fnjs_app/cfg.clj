; --                                                            ; {{{1
;
; File        : fnjs_app/cfg.clj
; Maintainer  : Felix C. Stegerman <flx@obfusk.net>
; Date        : 2012-06-06
;
; Copyright   : Copyright (C) 2012  Felix C. Stegerman
; Licence     : GPLv2 or EPLv1
;
; Depends     : ...
; Description : ...
;
; TODO        : ...
;
; --                                                            ; }}}1

(ns fnjs-app.cfg
  (:use [ clojure.java.io :only [ file ] ]) )

; --

(def app-vsn    "v0.0.1")
(def fnjs-vsn   "v0.1.1")

(def jquery-vsn "1.7.2")

(def copyright  "Copyright (C) 2012  Felix C. Stegerman")
(def fnjs-url   "https://github.com/obfusk/fnjs#readme")

; --

(def ex-dir "resources/public/examples")
(def ugly-cmd
  (clojure.string/split "uglifyjs -b -i 2 -nm -ns" #"\s+") )

; --

(def examples! (->> ex-dir file .listFiles (map #(.getName %1)) sort))

; vim: set tw=70 sw=2 sts=2 et fdm=marker :
