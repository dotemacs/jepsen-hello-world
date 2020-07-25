(ns foo.core
  (:require [jepsen.tests :as tests]
            [jepsen.generator :as gen]))

(defn foo
  [version]
  (assoc tests/noop-test
         :pure-generators true
         :generator (gen/phases
                     {:f :read}
                     (->> (gen/reserve
                           5 (repeat {:f :read})
                           (gen/mix
                            [(fn [] {:f :write
                                     :value (rand-int 5)})
                             (fn [] {:f :cas
                                     :value [(rand-int 5)
                                             (rand-int 5)]})]))
                          (gen/limit 100)
                          (gen/clients)))))
