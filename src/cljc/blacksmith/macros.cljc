(ns blacksmith.macros)

(defmacro seq-with-keys
  "Given a sequence, return a for loop with the keys itm and idx for
  each element of the sequence, and call the body."
  [seq body-fn]
  `(for [idx# (range (count ~seq))
         :let [itm# (nth ~seq idx#)]]
     (with-meta (~body-fn itm#) {:key idx#})))
