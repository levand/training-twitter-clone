(ns twitter-clone.view.tweets
  (:require [compojure.core :as c]
            [compojure.route :as route]
            [clojure.pprint :as pp]
            [hiccup.core :as h]))

(c/defroutes routes
             (c/GET "/list" [] "tweet list")
             (c/POST "/create" [] "new tweet"))
