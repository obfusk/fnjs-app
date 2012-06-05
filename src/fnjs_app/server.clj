; --                                                            ; {{{1
;
; File        : fnjs_app/server.clj
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

(ns fnjs-app.server
  (:require [ noir.server :as server ]) )

; --

(server/load-views "src/fnjs_app/views/")

(defn -main [& m]
  (let  [ mode  (keyword (or (first m) :dev))
          port  (Integer. (get (System/getenv) "PORT" "8080")) ]
    (server/start port { :mode mode, :ns 'fnjs-app }) ))

; vim: set tw=70 sw=2 sts=2 et fdm=marker :
