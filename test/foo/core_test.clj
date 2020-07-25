(ns foo.core-test
  (:require [clojure.test :refer [deftest is]]
            [jepsen.core :as jepsen]
            [foo.core :as foo]))

(deftest a-test
  (is (:valid? (:results (jepsen/run! (foo/foo "3.4.5+dfsg-2"))))))
