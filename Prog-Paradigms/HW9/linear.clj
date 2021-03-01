(defn f' [f]
  (fn [coll1 coll2]
    (mapv f coll1 coll2))
  )

(defn g' [f]
  (fn [coll val]
    (mapv (fn [a] (f a val)) coll)
    )
  )

(def s+ +)
(def s- -)
(def s* *)
(def sd /)

(def v+ (f' s+))
(def v- (f' s-))
(def v* (f' s*))
(def vd (f' sd))

(def m+ (f' v+))
(def m- (f' v-))
(def m* (f' v*))
(def md (f' vd))

(def c+ (f' m+))
(def c- (f' m-))
(def c* (f' m*))
(def cd (f' md))

(def v*s (g' s*))
(def m*s (g' v*s))

(defn scalar [v1 v2]
  (apply + (v* v1 v2))
  )

(defn vect [v1 v2]
  (let [ve (fn [i j] (s- (s* (nth v1 i) (nth v2 j)) (s* (nth v1 j) (nth v2 i))))]
    (vector (ve 1 2)
            (ve 2 0)
            (ve 0 1)
            ))
  )

(defn m*v [m v]
  (mapv (fn [a] (apply s+ (v* a v))) m)
  )

(defn transpose [m]
  (apply mapv vector m)
  )

(defn m*m [m1 m2]
  (let [tm (transpose m2)]
    (mapv (fn [m] (m*v tm m)) m1))
  )