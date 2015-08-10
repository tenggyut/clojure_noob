(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(println "cleanliness is next to godliness")

(defn train
  []
  (println "Chchoo1"))

(+ 1 (* 2 3 4))

(def failed-protagonist-names
  ["larry Potter"
   "Dorren the Explorer"
   "The Incredible Hulk"])

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "OH. MY. GOD. ", name "YOU ARE MOST DEFINITELY THE BEST"))


(too-enthusiastic "hahah")

(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def in3 (inc-maker 3))

(in3 7)

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn needs-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(defn make-matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps which have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts
            final-body-parts (conj final-body-parts part)]
        (if (needs-matching-part? part)
          (recur remaining (conj final-body-parts (make-matching-part part)))
          (recur remaining final-body-parts))))))

(symmetrize-body-parts asym-hobbit-body-parts)

(defn better-symetrize-body-parts
  "Expects a seq of maps which have :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (let [final-body-parts (conj final-body-parts part)]
              (if (needs-matching-part? part)
                (conj final-body-parts (make-matching-part part))
                final-body-parts)))
          []
          asym-body-parts))

(better-symetrize-body-parts asym-hobbit-body-parts)

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + 0 (map :size sym-parts))
        target (inc (rand body-part-size-sum))]
    (loop [[part & rest] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur rest (+ accumulated-size (:size part)))))))

(hit asym-hobbit-body-parts)

(defn titleize
  [topic]
  (str topic " for the Brave and True"))

(defn label-key-val
  [[key val]]
  (str "key: " key ", value: " val))
