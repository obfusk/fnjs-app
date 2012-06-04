(ns fnjs-app.views.core
  (:use     [ noir.core             :only [ defpage defpartial  ] ]
            [ fnjs-app.views.common :only [ layout              ] ]
            [ fnjs-app.core         :only [ fnjs                ] ] )
  (:require [ noir.response         :as     resp                ]
            [ hiccup.page-helpers   :as     page                ]   ) )

; --

(defpartial root-head []
  (page/include-css "/css/cm.css")
  (page/include-css "/css/app.css")
  (page/include-js  (str  "http://ajax.googleapis.com/ajax/libs/"
                          "jquery/1.7.2/jquery.min.js" ))
  (page/include-js  "/js/cm.js")
  (page/include-js  "/js/cm-clj.js")
  (page/include-js  "/js/cm-js.js")
  (page/include-js  "/js/app.js") )

(defpartial root-content []
  [:div.center
    [:p.fixed "fnjs - functional javascript - app"]
    [:button#fnjs.fixed "!fnjs"]
    [:button#js.fixed "js!"]
    [:hr]
    [:div#content
      [:div#in] [:div#out] [:div.clear] ]
    [:hr] ])

; --

(defpage "/" []
  (layout "fnjs-app" :head (root-head) :content (root-content)) )

(defpage [:post "/fnjs"] {:keys [data]}
  (resp/json (fnjs data)) )

; --
