; --                                                            ; {{{1
;
; File        : fnjs_app/core.clj
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

(ns fnjs-app.core
  (:use     [ clojure.java.shell  :only [ sh        ] ]
            [ fnjs-app.cfg        :only [ ugly-cmd  ] ] )
  (:require [ fnjs.core :as _c ]
            [ fnjs.misc :as _m ] ))

; --

(defn read-data [x] (_m/safe-read (str "(" x "\n)"))) ; MOVE      TODO

(defn uglify! [x]
  (let [ o (apply sh (concat ugly-cmd [:in x])) ]
    (if (= 0 (:exit o))
      (:out o)
      (throw (Exception. "uglifyjs returned non-zero")) )))

(defn fnjs [x ugly?]
  (let [ u (if ugly? uglify! identity) ]
    (try
      { :result (-> x read-data _c/fnjs u) }
    (catch Exception e
      { :error (str "fnjs: " (.getMessage e)) } ))))

; vim: set tw=70 sw=2 sts=2 et fdm=marker :
