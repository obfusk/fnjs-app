(ns fnjs-app.core
  (:require [ fnjs.core :as _c ]
            [ fnjs.misc :as _m ] ))

; --

(defn read-data [x] (_m/safe-read (str "(" x "\n)")))
(defn uglify!   [x] (str x " /* TODO: uglify! */"))

(defn fnjs [x]
  (try
    { :result (-> x read-data _c/fnjs uglify!) }
  (catch Exception e
    { :error (str "fnjs: " (.getMessage e)) } )))

; --
