(ns twitter-clone.model
  (:import [java.util Date]))

(defonce database (atom {:users {0 {:name "Luke" :id 0}
                                 1 {:name "Mike" :id 1}}
                         :tweets {0 {:id 0, :user 0, :time (.getTime (Date.))
                                     :content "Just ate a nice sandwich"}}}))
(defn user
  "Given a user id, return a user"
  [user-id]
  (get-in @database [:users user-id]))

(defn users
  "Return all users"
  []
  (vals (:users @database)))

(defn create-user
  "Pure function to add a user to the data"
  [db name]
  (let [next-id (count (:users db))]
    (assoc-in db [:users next-id]
              {:name name :id next-id})))

(defn create-user!
  "Create a user"
  [name]
  (let [new-data (swap! database create-user name)
        new-idx (dec (count (:users new-data)))]
    (get-in new-data [:users new-idx])))

