; --                                                            ; {{{1
;
; File        : fnjs_app/views/common.clj
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

(ns fnjs-app.views.common
  (:use [ noir.core           :only [ defpartial  ] ]
        [ hiccup.page-helpers :only [ html5       ] ] ))

; --

(defpartial layout [title & { :keys [ head content ] }]
  (html5
    [:head [:title title] head]
    [:body [:div#wrapper content]] ))

; vim: set tw=70 sw=2 sts=2 et fdm=marker :
