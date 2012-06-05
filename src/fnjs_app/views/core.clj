; --                                                            ; {{{1
;
; File        : fnjs_app/views/core.clj
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

(ns fnjs-app.views.core
  (:use     [ noir.core             :only [ defpage defpartial  ] ]
            [ hiccup.core           :only [ h                   ] ]
            [ fnjs-app.views.common :only [ layout              ] ]
            [ fnjs-app.core         :only [ fnjs                ] ] )
  (:require [ noir.response         :as resp  ]
            [ hiccup.page-helpers   :as page  ]
            [ fnjs-app.cfg          :as _c    ] ))

; --

(def title  "fnjs - functional javascript")
(def keybs  "ctrl+enter: |fnjs; alt+enter: run!")
(def vsns   (str "fnjs-app " _c/app-vsn "; fnjs " _c/fnjs-vsn)) ; TODO

; --

(defpartial root-head []                                        ; {{{1
  (page/include-css "/css/cm.css")
  (page/include-css "/css/app.css")
  (page/include-js  (str  "http://ajax.googleapis.com/ajax/libs/"
                          "jquery/" _c/jquery-vsn "/jquery.min.js" ))
  (page/include-js  "/js/cm.js")
  (page/include-js  "/js/cm-clj.js")
  (page/include-js  "/js/cm-js.js")
  (page/include-js  "/js/app.js") )
                                                                ; }}}1

(defpartial root-content []                                     ; {{{1
  [:div#page.center
    [:p (h title) [:br]
      [:small (h " --> ")
        [:a { :href (h _c/fnjs-url) } (h _c/fnjs-url)] ]]
    [:div
      [:div#examples
        [:select#ex
          (for [e (map h _c/examples!)] [:option { :value e } e]) ]
        [:button#load (h "load")] ]
      (h " /// fnjs --> ")
      [:button#fnjs (h "|fnjs")] (h " --> ")
      [:input#ugly  { :type "checkbox", :value (h "ugly")
                      :checked "checked" }] (h "|uglify")
      (h " --> js /// ") [:button#js (h "run!")] ]
    [:hr] [:div#content [:div#in] [:div#out] [:div.clear]] [:hr]
    [:div
      [:div#info "&nbsp;"]
      [:small (map h [keybs " /// " vsns " /// " _c/copyright])] ]])
                                                                ; }}}1

; --

(defpage "/" []
  (layout title :head (root-head) :content (root-content)) )

(defpage [:post "/fnjs"] {:keys [data as ugly]}
  (let [ s (fnjs data (= ugly "true")) ]
    (if (= as "text")
      (resp/content-type "text/javascript" (some s [:result :error]))
      (resp/json s) )))

; vim: set tw=70 sw=2 sts=2 et fdm=marker :
