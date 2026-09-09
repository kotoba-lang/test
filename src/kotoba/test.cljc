(ns kotoba.test
  "Assembled from one repo per definition.

  This namespace holds no implementation. It re-exports the definitions
  that each live in their own repo, so a call site can require one name
  and a library can require only the definitions it actually uses.

  NOT re-exported here, on purpose: IGenerator, RNG. A protocol's identity is what extend-type and reify dispatch on,
  and a copy would make an implementation silently extend nothing, so the
  protocol name stays in the one repo that declares it. Requiring that repo
  is a compile error away; a copy would not be.

  Macros are not re-exported: are, deftest, is, testing. A macro var cannot be copied -- (def x other/x) on one is a
  compile error: Can't take value of a macro. A forwarding
  defmacro would need :require-macros to work in ClojureScript.
  Require the repo that defines the macro.

  Value vars are not re-exported either: *contexts*, *current-test*, *report*, alphabet. `(def x other/x)` copies, which is harmless for a function and makes
  with-redefs through this namespace a SILENT no-op for a value -- measured
  on kotoba.lang.edn, where three assertions passed against nothing at all.
  Require the repo that defines the value.
"
  (:require [kotoba.test.generator :as igenerator-ns]
            [kotoba.test.rng :as rng-ns]
            [kotoba.test.are-eq :as are-eq-ns]
            [kotoba.test.for-all :as for-all-ns]
            [kotoba.test.gen-bool :as gen-bool-ns]
            [kotoba.test.gen-element :as gen-element-ns]
            [kotoba.test.gen-from-spec :as gen-from-spec-ns]
            [kotoba.test.gen-int :as gen-int-ns]
            [kotoba.test.gen-string :as gen-string-ns]
            [kotoba.test.make-rng :as make-rng-ns]
            [kotoba.test.next-int :as next-int-ns]
            [kotoba.test.next-long :as next-long-ns]
            [kotoba.test.quickcheck :as quickcheck-ns]
            [kotoba.test.record-error :as record-error-ns]
            [kotoba.test.record-fail :as record-fail-ns]
            [kotoba.test.record-pass :as record-pass-ns]
            [kotoba.test.register-test :as register-test-ns]
            [kotoba.test.registered-tests :as registered-tests-ns]
            [kotoba.test.run-tests :as run-tests-ns]
            [kotoba.test.run-tests-report :as run-tests-report-ns]
            [kotoba.test.shrink-int :as shrink-int-ns]
            [kotoba.test.throws :as throws-ns]))

(def ->RNG "See kotoba.test.rng/->RNG." rng-ns/->RNG)
(def are-eq? "See kotoba.test.are-eq/are-eq?." are-eq-ns/are-eq?)
(def for-all "See kotoba.test.for-all/for-all." for-all-ns/for-all)
(def gen "See kotoba.test.generator/gen." igenerator-ns/gen)
(def gen-bool "See kotoba.test.gen-bool/gen-bool." gen-bool-ns/gen-bool)
(def gen-element "See kotoba.test.gen-element/gen-element." gen-element-ns/gen-element)
(def gen-from-spec "See kotoba.test.gen-from-spec/gen-from-spec." gen-from-spec-ns/gen-from-spec)
(def gen-int "See kotoba.test.gen-int/gen-int." gen-int-ns/gen-int)
(def gen-string "See kotoba.test.gen-string/gen-string." gen-string-ns/gen-string)
(def make-rng "See kotoba.test.make-rng/make-rng." make-rng-ns/make-rng)
(def map->RNG "See kotoba.test.rng/map->RNG." rng-ns/map->RNG)
(def next-int "See kotoba.test.next-int/next-int." next-int-ns/next-int)
(def next-long "See kotoba.test.next-long/next-long." next-long-ns/next-long)
(def quickcheck "See kotoba.test.quickcheck/quickcheck." quickcheck-ns/quickcheck)
(def record-error! "See kotoba.test.record-error/record-error!." record-error-ns/record-error!)
(def record-fail! "See kotoba.test.record-fail/record-fail!." record-fail-ns/record-fail!)
(def record-pass! "See kotoba.test.record-pass/record-pass!." record-pass-ns/record-pass!)
(def register-test! "See kotoba.test.register-test/register-test!." register-test-ns/register-test!)
(def registered-tests "See kotoba.test.registered-tests/registered-tests." registered-tests-ns/registered-tests)
(def run-tests "See kotoba.test.run-tests/run-tests." run-tests-ns/run-tests)
(def run-tests-report "See kotoba.test.run-tests-report/run-tests-report." run-tests-report-ns/run-tests-report)
(def shrink-int "See kotoba.test.shrink-int/shrink-int." shrink-int-ns/shrink-int)
(def throws? "See kotoba.test.throws/throws?." throws-ns/throws?)
