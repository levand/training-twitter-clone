(ns twitter-clone.server
  "Entry point for app"
  (:require [twitter-clone.view :as view]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as kw-params]
            [ring.adapter.jetty :as jetty]))

(def handler (-> view/routes
                 kw-params/wrap-keyword-params
                 params/wrap-params))

(defn serve
  "Start the server"
  [port]
  (jetty/run-jetty #'handler {:port port :join? false}))

