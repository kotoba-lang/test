# kotoba-lang/test

[![CI](https://github.com/kotoba-lang/test/actions/workflows/ci.yml/badge.svg)](https://github.com/kotoba-lang/test/actions/workflows/ci.yml)

**Layer 4 (tooling) of the kotoba foundational stdlib** — property testing and
assertion helpers. Consumes the sibling [`kotoba-lang/spec`](https://github.com/kotoba-lang/spec)
for generators, so **a spec is also a test generator** — the proptest /
test.check / deno.test equivalent for kotoba. No third-party deps; every
namespace is `.cljc` (JVM / SCI / ClojureScript / GraalVM / kotoba-WASM). See
[`docs/adr/ADR-kotoba-lang-foundational-stdlib.md`](https://github.com/kotoba-lang/kotoba-lang/blob/main/docs/adr/ADR-kotoba-lang-foundational-stdlib.md).

## Why

`clojure.test` covers example-based tests; kotoba also needs **property tests**
that hold for *all* inputs a spec can generate. `test` provides a deterministic
PRNG, a `gen` protocol a spec implements, and `quickcheck` / `for-all` that
shrink failing cases — pure functions, no host, runs on kotoba-WASM. This makes
`kotoba-lang/test` a consumer of `kotoba-lang/spec` (the M5 milestone for spec:
an external lib depends on it).

## Current surface

`kotoba.lang.test`:

- `make-rng` — deterministic PRNG from a seed (LCG, pure)
- `IGenerator` protocol: `gen` → `[value rng']`
- primitive generators: `gen-int`, `gen-bool`, `gen-element`, `gen-string`
- `quickcheck` — run a property `n` times with seeded rng; returns
  `{:pass? bool :seed :runs :smallest :shrink-depth}` on failure
- `for-all` — declarative property over bound generators
- `shrink` — integer shrinking toward zero (the default shrink strategy)
- `are-eq?` / `throws?` — small assertion helpers

## Install

```clojure
io.github.kotoba-lang/test {:git/sha "<sha>"}
```

## Use

```clojure
(require '[kotoba.lang.test :as pt])

;; a property: for all ints, x+x == 2*x
(pt/quickcheck (fn [x] (= (* x 2) (+ x x))) #(pt/gen-int %) :seed 42 :runs 100)
;;=> {:pass? true :seed 42 :runs 100}

;; a failing property shrinks to the smallest counterexample
(pt/quickcheck (fn [x] (pos? x)) #(pt/gen-int % [-10 10]) :seed 1 :runs 100)
;;=> {:pass? false :smallest 0 ...}
```

## Verify

```sh
clojure -M:test
```
