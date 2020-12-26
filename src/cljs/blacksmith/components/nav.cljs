(ns blacksmith.components.nav)

(defn nav
  []
  [:nav {:class "bg-gray-800"}
   [:div {:class "max-w-7xl mx-auto px-2 sm:px-6 lg:px-8"}
    [:div {:class "relative flex items-center justify-between h-16"}

     ;; left div
     [:div {:class "absolute inset-y-0 left-0 flex items-center sm:hidden"}
      [:button {:class "inline-flex items-center justify-center p-2 rounded-md text-gray-400 hover:text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white", :aria-expanded "false"}
       [:span {:class "sr-only"} "Open main menu"]  
       [:svg {:class "block h-6 w-6", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 24 24", :stroke "currentColor", :aria-hidden "true"}
        [:path {:stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M4 6h16M4 12h16M4 18h16"}]]
       [:svg {:class "hidden h-6 w-6", :xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 24 24", :stroke "currentColor", :aria-hidden "true"}
        [:path {:stroke-linecap "round", :stroke-linejoin "round", :stroke-width "2", :d "M6 18L18 6M6 6l12 12"}]]]]
     [:div {:class "flex-1 flex items-center justify-center sm:items-stretch sm:justify-start"}
      [:div {:class "flex-shrink-0 flex items-center"}
       [:img {:class "hidden lg:block h-8 w-auto", :src "/img/blacksmith-logo.png", :alt "Blacksmith"}]]
      [:div {:class "hidden sm:block sm:ml-6"}
       [:div {:class "flex space-x-4"}
        [:a {:href "#", :class "bg-gray-900 text-white px-3 py-2 rounded-md text-sm font-medium"} "Home"]
        [:a {:href "#", :class "text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"} "About"]]]]

     ;; right div
     [:div {:class "absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0"}
      [:button {:class "button-primary"} "Login"]
      [:button {:class "bg-purple-700 m-1 px-3 py-2 rounded-lg text-gray-300 hover:text-white focus:outline-none"} "Sign up"]  
      [:div {:class "ml-3 relative"}]]]]])
