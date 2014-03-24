(ns twitter-clone.view
  (:require [twitter-clone.view.user :as user]
            [twitter-clone.view.tweets :as tweets]
            [compojure.core :as c]
            [compojure.route :as route]
            [clojure.pprint :as pp]
            [hiccup.core :as h]))

(c/defroutes routes
             (c/GET "/" [] (h/html [:html {}
                                    [:head {}
                                     [:title {} "Twitter Clone"]]
                                    [:body {}
                                     [:h1 {} "Hello, world!"]]]))
             (c/context "/user" [] user/routes)
             (c/context "/tweets" [] tweets/routes)
             (route/not-found (h/html [:html {}
                                       [:head {}
                                        [:title {} "404"]]
                                       [:body {}
                                        [:h1 {} "You fail. Do not pass go. Do not collect $200"]]])))


