(ns blacksmith.macros)

(defmacro seq-with-keys
  "Given a sequence, call the body-fn with each item, attaching the
  items index as metadata to the result. "
  [seq body-fn]
  `(for [idx# (range (count ~seq))
         :let [itm# (nth (seq ~seq) idx#)]]
     (with-meta (~body-fn itm#) {:key idx#})))
