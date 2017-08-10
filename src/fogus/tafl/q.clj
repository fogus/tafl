(ns fogus.svedn.q
  (:require clojure.set))

(defn has-multiple [key table]
  (clojure.set/select (fn [entity] 
                        (let [cell (get entity key)] 
                          (and (instance? java.util.Set cell)
                               (< 1 (count cell)))))
                      table))

(defn has-invalid 
  [key table]
   (clojure.set/select (fn [entity] 
                         (let [cell (get entity key)] 
                           (= :clojure.spec/invalid cell)))
                       table))

(defn ^:private keys-on-fn [f]
  (fn [entry]
    (reduce-kv (fn [acc k v]
                 (if (f v)
                   (conj acc k)
                   acc))
               #{}
               entry)))

;; TODO: This isn't exactly what I want, but it'll do for now.
(defn partial-enum [sub-enum]
  (fn [enum]
    (when enum
      (let [pstr (.substring (str sub-enum) 1)
            estr (.substring (str enum) 1)]
        (.contains estr pstr)))))

(defn ^:private maybe-set [f]
  (fn [v]
    (if (instance? java.util.Set v)
      (reduce #(or %1 (f %2)) false v)
      (f v))))

(defn on-value 
  ([f table]
   (->> (seq table)
        (map (keys-on-fn (maybe-set f)))
        (map (fn [entry ks]
               (when (seq ks) entry)) 
             table)
        (filter identity)
        set))
  ([key f table]
   (clojure.set/select (comp (maybe-set f) key) table)))
