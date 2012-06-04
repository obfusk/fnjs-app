(ns fnjs-app.views.core
  (:use     [ noir.core             :only [ defpage defpartial  ] ]
            [ hiccup.core           :only [ h                   ] ]
            [ fnjs-app.views.common :only [ layout              ] ]
            [ fnjs-app.core         :only [ fnjs                ] ] )
  (:require [ noir.response         :as     resp                ]
            [ hiccup.page-helpers   :as     page                ]   ) )

; --

(def copyright
  (h "Copyright (C) 2012  Felix C. Stegerman <flx@obfusk.net>") )

(defpartial root-head []                                        ; {{{1
  (page/include-css "/css/cm.css")
  (page/include-css "/css/app.css")
  (page/include-js  (str  "http://ajax.googleapis.com/ajax/libs/"
                          "jquery/1.7.2/jquery.min.js" ))
  (page/include-js  "/js/cm.js")
  (page/include-js  "/js/cm-clj.js")
  (page/include-js  "/js/cm-js.js")
  (page/include-js  "/js/app.js") )
                                                                ; }}}1

(defpartial root-content []                                     ; {{{1
  [:div.center.fixed
    [:p "fnjs - functional javascript - app"]
    [:button#fnjs.fixed "|fnjs"]
    [:button#js.fixed "!js"]
    [:input#ugly  { :type "checkbox", :value "ugly"
                    :checked "checked" }] "|uglify"
    [:hr] [:div#content [:div#in] [:div#out] [:div.clear]] [:hr]
    [:div#info "&nbsp;"]
    [:p.small (h "[C-Return -> |fnjs; A-Return -> !js]")]
    [:p.small copyright] ])
                                                                ; }}}1

; --

(defpage "/" []
  (layout "fnjs-app" :head (root-head) :content (root-content)) )

(defpage [:post "/fnjs"] {:keys [data as ugly]}
  (let [ s (fnjs data (= ugly "true")) ]
    (if (= as "text")
      (resp/content-type "text/javascript"
        (some s [:result :error]) )
      (resp/json s) )))

; --
