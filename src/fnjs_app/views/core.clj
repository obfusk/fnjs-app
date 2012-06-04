(ns fnjs-app.views.core
  (:use     [ noir.core             :only [ defpage defpartial  ] ]
            [ fnjs-app.views.common :only [ layout              ] ] )
;           [ fnjs-app.core         :only [ ...                 ] ] )
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
  [:h1 "fnjs - functional javascript - app"]
  [:div#content
    [:div#in] [:div#out] ]
  [:button#fnjs.center "!fnjs"] )

; --

(defpage "/" []
  (layout :head (root-head) :content (root-content)) )

(defpage [:post "/fnjs"] {:keys [data]}
  (resp/content-type "text/javascript" "...") )

; --
