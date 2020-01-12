*Notice*: This library in no longer maintained and may no longer compile. See [moroccode](https://github.com/gabrielshanahan/moroccode) for similar functionality.

# HashKode
HashKode is a small Kotlin hashcode utility to help you create easy and bugfree hashcodes and equality checks.

## Why?
Java identifies objects by their `hashcode` and their `equals` method. If you do not override these methods, the JVM can only check objects for referential equality, not structural equality. ([Read more](https://kotlinlang.org/docs/reference/equality.html#equality))

Kotlin generates these methods [automatically](https://kotlinlang.org/docs/reference/data-classes.html#data-classes) when you create a `data class`, but sometimes this is not preferred.

Implementing `hashcode` and `equals` can be tedious, verbose and is bug prone. HashKode provides concise ways to override these methods.

## Features
- Lightweight: **14.2 KB**
- [Very fast](#benchmarks)
- Designed with Josh Bloch's *Effective Java* in mind
- Uses Java hashCode methods: tested, high quality hashes
- Able to list differences

---
  
# Download
HashKode can be downloaded from the [Maven Central Repository](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.pvdberg1998%22%20AND%20a%3A%22hashkode%22).

HashKode is released under the [MIT license](LICENSE.md).

## Alternatives
- [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/builder/HashCodeBuilder.html) (483 KB: +468.8 KB)
- [Google Guava](https://github.com/google/guava/wiki/CommonObjectUtilitiesExplained) (55 KB: +40.8 KB)

---

# How to use
- [Hashcodes](#hashcodes)
- [Equality](#equality)
- [Difference](#difference)

---

## Hashcodes
HashKode makes creating hashes of your fields extremely convenient: just pass your fields to `hashKode`.
```kotlin
override fun hashCode() = hashKode(
    field1, field2, field3, field4
)
```
`hashKode` needs an odd number and a prime number to calculate hashes. (By default: 17 and 37.)
When changing these values, HashKode validates them. To disable this behaviour, you can set `HashKode.VERIFY_HASHKODE_PARAMETERS` to `false`. 
```kotlin 
HashKode.VERIFY_HASHKODE_PARAMETERS = false 
// ... 
override fun hashCode() = hashKode(field1, field2, initialOddNumber = 3, multiplierPrime = 7) 
``` 

---

## Equality
Checking two objects for structural equality can be done by overriding the `equals` method and using HashKodes `compareFields`. 
```Kotlin
override fun equals(other: Any?) = compareFields(other)
{
    // Compare in here
}
``` 

In `compareFields`, 3 fields are available:

1) `one` : The first object (`this`)
2) `two` : The second object (`other`)
3) `equal` : Indicates if the objects are equal

### Abstraction
There are 3 levels of abstraction available*.

1) `compareField`
2) `correspondsTo`
3) Regular `(x == y)`

You can add custom comparators by using `compareBy`.

### CompareField
`compareField` takes a method reference to a getter and automatically uses this getter on the two objects being compared.
```kotlin
override fun equals(other: Any?) = compareFields(other)
{
    compareField(Example::field1)
    compareField(Example::field2)
    compareField(Example::field3)
}
```

### CorrespondsTo
`correspondsTo` compares two fields you pass to it. It declares that a field should *correspond to* another field.
```kotlin
override fun equals(other: Any?) = compareFields(other)
{
    one.field1 correspondsTo two.field1
    one.field2 correspondsTo two.field2
    one.field3 correspondsTo two.field3
}
```
Note that it is possible to let a field correspond to a different field: `one.field1 correspondsTo two.field2`.

### Regular `(x == y)`
The fastest way to check fields for equality is a regular `==` check. Remember to set the `equal` field manually.
```kotlin
override fun equals(other: Any?) = compareFields(other)
{
    equal = one.field1 == two.field1 &&
            one.field2 == two.field2 &&
            one.field3 == two.field3
}
```

*Note that higher abstraction means lower performance, but this probably **will not** be an issue. See [benchmarks](#benchmarks).

---

## Difference
HashKode can list differences between fields of objects. The API is the same as when using `compareFields`.
```kotlin
val tester1 = Example(field = "Hello")
val tester2 = Example(field = "World")

val diff = tester1.getDifferences(tester2)
{
    compareField(BasicTester::field)
}
```
`diff` will now contain a single `FieldDifference` object, since one different field was found.
`FieldDifference` contains 2 pairs of a field and its owner object.

A custom comparison can be done by using `differenceBy`.

---

## Benchmarks
HashKode can outperform alternative libraries.

### Benchmark setup
- Intel core i5 6600k @3.50Ghz
- 16 GB DDR4 2400MHz RAM
- Windows 10.0.14393

### Benchmark details
- Object with 5 fields:
    - Integer (index)
    - String (`Hello World`)
    - Double (`Math.PI`)
    - Boolean (`true`)
    - Array<String> (`["A", "B", "C", "D", "E"]`)
- Asserted equals check repeated 50.000.000
- Test ran 3 times
- Libraries tested: `HashKode`, `Guava`, `Apache`

### CompareField
| Library      | Average time per equals check
| :---:        | :---:
| HashKode     | 48.3 ns
| Guava        | 33.1 ns
| Apache       | 29.7 ns

### CorrespondsTo
| Library      | Average time per equals check
| :---:        | :---:
| HashKode     | 39.6 ns
| Guava        | 33.2 ns
| Apache       | 29.8 ns

### Regular `(x == y)`
| Library      | Average time per equals check
| :---:        | :---:
| HashKode     | **28.4 ns**
| Guava        | 33.1 ns
| Apache       | 29.7 ns
