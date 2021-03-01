(defn constant [value]
  (fn [xyz] value)
  )

(defn variable [value]
  (fn [xyz] (get xyz value))
  )

(defn binary [f]
  (fn [a b]
    (fn [xyz]
      (f (a xyz) (b xyz))
      )
    )
  )

(defn unary [f]
  (fn [x]
    (fn [xyz]
      (f (x xyz))
      )
    )
  )

(defn div [a b]
  (/ (double a) (double b))
  )

(defn e [a]
  (Math/exp a)
  )

(defn l [a]
  (Math/log (Math/abs a))
  )

(def add (binary +))
(def subtract (binary -))
(def multiply (binary *))
(def divide (binary div))

(def negate (unary -))
(def exp (unary e))
(def ln (unary l))


(def opmap {'+      add,
            '-      subtract,
            '*      multiply,
            '/      divide,
            'negate negate,
            'exp    exp,
            'ln     ln
            })

(defn parse1 [expression]
  (cond
    (list? expression) (apply (get opmap (first expression)) (mapv parse1 (rest expression)))
    (number? expression) (constant expression)
    (symbol? expression) (variable (str expression))
    )
  )

(defn parseFunction [expression]
  (parse1 (read-string expression))
  )

"HW 11"

(definterface IExpression
  (ev [x])
  (toStr [])
  (toStrSuf [])
  (df [x])
  )

(defn evaluate [obj x] (.ev obj x))
(defn toString [obj] (.toStr obj))
(defn toStringSuffix [obj] (.toStrSuf obj))
(defn diff [obj x] (.df obj x))

(declare ZERO)
(declare ONE)

(deftype Constant' [value]
  IExpression
  (ev [this xyz] value)
  (toStr [this] (format "%.1f" value))
  (toStrSuf [this] (format "%.1f" value))
  (df [this var] ZERO)
  )

(deftype Variable' [value]
  IExpression
  (ev [this xyz] (get xyz (clojure.string/lower-case (first (seq value)))))
  (toStr [this] value)
  (toStrSuf [this] value)
  (df [this var] (if (= (clojure.string/lower-case (first (seq value))) var) ONE ZERO))
  )

(defn Constant [value] (Constant'. value))
(defn Variable [value] (Variable'. value))

(def ZERO (Constant 0.0))
(def ONE (Constant 1.0))

(deftype BinOperation [op sign dfn a b]
  IExpression
  (ev [this xyz] (op (.ev a xyz) (.ev b xyz)))
  (toStr [this] (str "(" (str sign " " (.toStr a) " " (.toStr b)) ")"))
  (toStrSuf [this] (str "(" (str (.toStrSuf a) " " (.toStrSuf b)) " " sign ")"))
  (df [this xyz] (dfn a b (diff a xyz) (diff b xyz)))
  )

(defn Add [a b] (BinOperation. + "+" (fn [a b da db] (Add da db)) a b))
(defn Subtract [a b] (BinOperation. - "-" (fn [a b da db] (Subtract da db)) a b))
(defn Multiply [a b] (BinOperation. * "*" (fn [a b da db] (Add (Multiply da b) (Multiply a db))) a b))
(defn Divide [a b] (BinOperation. div "/" (fn [a b da db] (Divide (Subtract (Multiply da b) (Multiply a db)) (Multiply b b))) a b))

(deftype UnOperation [op sign dfn a]
  IExpression
  (ev [this xyz] (op (.ev a xyz)))
  (toStr [this] (str "(" (str (.toStr a))  " " sign ")"))
  (toStrSuf [this] (str "(" (str (.toStrSuf a)) " " sign ")"))
  (df [this xyz] (dfn a (diff a xyz)))
  )

(defn Negate [a] (UnOperation. - "negate" (fn [a da] (Negate da)) a))
(defn Exp [a] (UnOperation. e "exp" (fn [a da] (Multiply da (Exp a))) a))
(defn Ln [a] (UnOperation. l "ln" (fn [a da] (Multiply da (Divide ONE a))) a))

(def objmap {"+"      Add,
             "-"      Subtract,
             "*"      Multiply,
             "/"      Divide,
             "negate" Negate,
             "exp"    Exp,
             "ln"     Ln
             })

(defn parse2 [expression]
  (cond
    (list? expression) (apply (get objmap (first expression)) (mapv parse2 (rest expression)))
    (number? expression) (Constant expression)
    (symbol? expression) (Variable (str expression))
    )
  )

(defn parseObject [expression]
  (parse2 (read-string expression))
  )

"HW 12"
"from kgeorgiy"

(defn -return [value tail] {:value value :tail tail})
(def -valid? boolean)
(def -value :value)
(def -tail :tail)

(defn _show [result]
  (if (-valid? result) (str "-> " (pr-str (-value result)) " | " (pr-str (apply str (-tail result))))
                       "!"))
(defn tabulate [parser inputs]
  (run! (fn [input] (printf "    %-10s %s\n" (pr-str input) (_show (parser input)))) inputs))
(defn _empty [value] (partial -return value))

(defn _char [p]
  (fn [[c & cs]]
    (if (and c (p c)) (-return c cs))))

(defn _map [f result]
  (if (-valid? result)
    (-return (f (-value result)) (-tail result))))

(defn _combine [f a b]
  (fn [str]
    (let [ar ((force a) str)]
      (if (-valid? ar)
        (_map (partial f (-value ar))
              ((force b) (-tail ar)))))))

(defn _either [a b]
  (fn [str]
    (let [ar ((force a) str)]
      (if (-valid? ar) ar ((force b) str)))))

(defn _parser [p]
  (fn [input]
    (-value ((_combine (fn [v _] v) p (_char #{\u0000})) (str input \u0000)))))

(defn +char [chars] (_char (set chars)))
(defn +char-not [chars] (_char (comp not (set chars))))
(defn +map [f parser] (comp (partial _map f) parser))
(def +parser _parser)
(def +ignore (partial +map (constantly 'ignore)))
(defn iconj [coll value] (if (= value 'ignore) coll (conj coll value)))
(defn +seq [& ps] (reduce (partial _combine iconj) (_empty []) ps))
(defn +seqf [f & ps] (+map (partial apply f) (apply +seq ps)))
(defn +seqn [n & ps] (apply +seqf (fn [& vs] (nth vs n)) ps))
(defn +or [p & ps] (reduce _either p ps))
(defn +opt [p] (+or p (_empty nil)))
(defn +star [p] (letfn [(rec [] (+or (+seqf cons p (delay (rec))) (_empty ())))] (rec)))
(defn +plus [p] (+seqf cons p (+star p)))
(defn +str [p] (+map (partial apply str) p))

(def *digit (+char "0123456789"))
;(def *number (+map read-string (+str (+plus *digit))))
(def *space (+char " \t\n\r"))
(def *ws (+ignore (+star *space)))
(def *all-chars (mapv char (range 32 128)))
(apply str *all-chars)
;(def *letter (+char (apply str (filter #(Character/isLetter %) *all-chars))))

"my code"

(declare *expression)

(defn +val [p] (+seqn 0 *ws p *ws))

(def *number (+val (+seqf
                     (fn [sign int dot frac] (read-string (str sign (apply str int) dot (apply str frac))))
                     (+opt (+char "+-"))
                     (+plus *digit)
                     (+opt (+char "."))
                     (+star *digit)
                     ))
  )
(def *letter (+char "xyzXYZ"))
(def *var (+val (+str (+plus *letter))))

(def *variable (+map Variable *var))
(def *constant (+map (comp Constant double) *number))

(def *bin-op (+val (+str (+seq (+char "+-/*")))))
(def *un-op (+val (+str (+or (+seq (+char "n") (+char "e") (+char "g") (+char "a") (+char "t") (+char "e"))))))

(def *args (+or *variable *constant (delay *expression)))

(def +ignore-char (comp +ignore +char))

(def bin (+val (+seq (+ignore-char "(") *args *args *bin-op (+ignore-char ")"))))
(def un (+val (+seq (+ignore-char "(") *args *un-op (+ignore-char ")"))))

(def *binary (+map (fn [coll] ((get objmap (nth coll 2)) (nth coll 0) (nth coll 1))) bin))
(def *unary (+map (fn [coll] ((get objmap (nth coll 1)) (nth coll 0))) un))

(def *expression (+or *binary *unary *variable *constant))

(def parseObjectSuffix (+parser *expression))