# HashKode
HashKode is a small Kotlin hashcode utility to help you create easy and bugfree hashcodes and equality checks.

## Why?
Java identifies objects by their `hashcode` and their `equals` method. If you do not override these methods, the JVM can only check objects for referential equality, not structural equality. ([Read more](https://kotlinlang.org/docs/reference/equality.html#equality))

Kotlin generates these methods [automatically](https://kotlinlang.org/docs/reference/data-classes.html#data-classes) when you create a `data class`, but sometimes this is not preferred.

Implementing `hashcode` and `equals` can be tedious, verbose and is bug prone. HashKode provides concise ways to override these methods.

## Features
- Lightweight (6.9 KB)
- Faster than alternatives by using `inlining` (thanks, Kotlin!)
- Designed with Josh Bloch's *Effective Java* in mind
- Optional parameter verification for higher performance

---
  
# Download
HashKode will soon be available on the [Maven Central Repository]!
HashKode is released under the [MIT license](LICENSE.md).

## Alternatives
- [Apache Commons Lang3](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/HashCodeBuilder.html) (483 KB)
- [Google Guava](https://github.com/google/guava/wiki/CommonObjectUtilitiesExplained) (2.45 MB)

---

# How to use
- [Hashcodes](#hashcodes)
- [Equality](#equality)
