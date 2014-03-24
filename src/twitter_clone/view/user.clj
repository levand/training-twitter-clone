(ns twitter-clone.view.user
  (:require [twitter-clone.model :as model]
            [compojure.core :as c]
            [compojure.route :as route]
            [clojure.pprint :as pp]
            [ring.util.response :as resp]
            [hiccup.core :as h]))

(defn view-user
  "View a user"
  [id]
  (let [user (model/user id)]
    (h/html [:html {}
             [:head {}
              [:title {} (:name user)]]
             [:body {}
              [:h1 {} (format "Viewing %s's profile" (:name user))]]])))


(defn list-users
  "list users"
  []
  (h/html [:html {}
           [:head {}
            [:title {} "All Users"]]
           [:body {}
            (apply vector :ul {}
                   (map (fn [u]
                          [:li {} (str (:name u) ": " (:id u))])
                        (model/users)))]]))

(defn create-user!
  "Create a user"
  [name]
  (let [user (model/create-user! name)]
    (println user)
    (resp/redirect (str "/user/view/" (:id user)))))

(defn new-user-form
  "Render the form for creating a user"
  []
  (h/html [:html {}
           [:head {}
            [:title {} "New User"]]
           [:body {}
            [:form {:action "/user/create"
                    :method "post"}
             [:input {:name "name"}]
             [:input {:type "submit"}]]]]))

(c/defroutes routes
             (c/GET "/view/:id" [id] (view-user (Long/parseLong id)))
             (c/GET "/new" [] (new-user-form))
             (c/GET "/list" [] (list-users))
             (c/POST "/create" [name]
                     (create-user! name)))