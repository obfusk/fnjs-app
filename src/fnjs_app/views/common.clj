(ns fnjs-app.views.common
  (:use [ noir.core           :only [ defpartial  ] ]
        [ hiccup.page-helpers :only [ html5       ] ] ))

; --

(defpartial layout [& { :keys [ head content ] }]
  (html5
    [:head
      [:title "fnjs-app"] head ]
    [:body
      [:div#wrapper content] ]))

; --
