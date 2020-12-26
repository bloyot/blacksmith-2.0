(ns blacksmith.views.characters)

(defn- header []
  [:div {:class "flex justify-between my-2"}
   [:div {:class "flex items-center text-2xl text-gray-600"} "Characters"]
   [:button {:class "button-primary"} "New Character"]])

(defn view
  "Render the main view for the characters page"
  [route-params]
  ;; header
  [:div {:class "p-4"}
   [header]
   [:hr]
   [:div "foo"]])
