(ns fnjs-app.views.common
  (:use [ noir.core           :only [ defpartial  ] ]
        [ hiccup.page-helpers :only [ html5       ] ] ))

; --

(defpartial layout [title & { :keys [ head content ] }]         ; {{{1
  (html5
    [:head
      [:title title] head ]
    [:body
      [:div#wrapper content] ]))
                                                                ; }}}1

; --
