# HashKode
HashKode is a small Kotlin hashcode utility to help you create easy and bugfree hashcodes and equality checks.

## Why?
Java identifies objects by their `hashcode` and their `equals` method. If you do not override these methods, the JVM can only check objects for referential equality, not structural equality. ([Read more](https://kotlinlang.org/docs/reference/equality.html#equality))

Kotlin generates these methods [automatically](https://kotlinlang.org/docs/reference/data-classes.html#data-classes) when you create a `data class`, but sometimes this is not preferred.

Implementing `hashcode` and `equals` can be tedious, verbose and is bug prone. HashKode provides concise ways to override these methods.

## Features
- Lightweight: **6.9 KB**
- Faster than alternatives by using `inlining` (thanks, Kotlin!)
- Designed with Josh Bloch's *Effective Java* in mind
- Using Java hashCode methods: tested, high quality hashes

---
  
# Download
HashKode will soon be available on the **Maven Central Repository**!
HashKode is released under the [MIT license](LICENSE.md).

## Alternatives
- [Apache Commons Lang3](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/HashCodeBuilder.html) (483 KB)
- [Google Guava](https://github.com/google/guava/wiki/CommonObjectUtilitiesExplained) (2.45 MB)

---

# How to use
- [Hashcodes](#hashcodes)
- [Equality](#equality)

---

## Hashcodes
To create a hashcode, use the `hashKode` method and pass the fields of your class.
```Kotlin
class Example(val field1: String = "Test", val field2: Double = Math.PI)
{
    override fun hashCode() = hashKode(field1, field2)
}
```
`hashKode` needs an odd number and a prime number to calculate hashes. By default these are set to 17 and 37. When changing these values, HashKode validates them. To disable this behaviour, you can set `HashKode.VERIFY_HASHKODE_PARAMETERS` to `false`.
```Kotlin
HashKode.VERIFY_HASHKODE_PARAMETERS = false
// ...
override fun hashCode() = hashKode(f1, f2, initialOddNumber = 3, multiplierPrime = 7)
```

---

## Equality
Checking two objects for structural equality can be done by overriding the `equals` method and using HashKodes `testEquality`.
```Kotlin
class Example(val field1: String = "Test", val field2: Double = Math.PI)
{
    override fun equals(other: Any?) = testEquality(
            other,
            { it.field1 == this.field1 },
            { it.field2 == this.field2 }
    )
}
```
As seen in the code above, `testEquality` requires you to add a lambda for each field you want to check.
