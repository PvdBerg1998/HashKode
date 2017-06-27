# HashKode
HashKode is a small Kotlin hashcode utility to help you create easy and bugfree hashcodes and equality checks.

## Why?
Java identifies objects by their `hashcode` and their `equals` method. If you do not override these methods, the JVM can only check objects for referential equality, not structural equality.
Kotlin generates these methods [automatically](https://kotlinlang.org/docs/reference/data-classes.html#data-classes) when you create a `data class`, but sometimes this is not preferred.

## Features
- Lightweight
- Designed with Josh Bloch's *Effective Java* in mind
- Optional parameter verification for higher performance

---
  
# Download
HashKode will soon be available on the [Maven Central Repository]!
HashKode is released under the [MIT license](LICENSE.md).

---

# How to use
- [Hashcodes](#hashcodes)
- [Equality](#equality)
