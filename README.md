strman-java
------

A Java 8 string manipulation library without any dependencies. It is inspired by [dleitee/strman](https://github.com/dleitee/strman).


## Available Functions

* [append](https://github.com/shekhargulati/strman-java#appendvalue-strings)
* [appendArray](https://github.com/shekhargulati/strman-java#appendarrayvalue-strings)
* [at](https://github.com/shekhargulati/strman-java#atvalue-index)
* [between](https://github.com/shekhargulati/strman-java#betweenvalue-start-end)
* [between](https://github.com/shekhargulati/strman-java#betweenvalue-start-end)
* [chars](https://github.com/shekhargulati/strman-java#charsvalue)
* [collapseWhitespace](https://github.com/shekhargulati/strman-java#collapsewhitespacevalue)


## append(value, strings...)

This method will append `strings` to the `value`.

```java
import static strman.Strman.append;
String title = "s";
String result = append(title, "tr","m","an");
// result = "strman"
```


## appendArray(value, strings)

This method will append all the values in `strings` array to the `value`.

```java
import static strman.Strman.appendArray;
String title = "s";
String result = appendArray(title, new String[]{"tr","m","an"});
// result = "strman"
```

## at(value, index)

Gets the character at index.

```java
import static strman.Strman.at;
String result = at("foobar", 0);
// result = "f"
```

## between(value, start, end)

Returns array with strings between `start` and `end`.

```java
import static strman.Strman.between;
String[] parts = between("[abc][def]", "[", "]";
// parts = ["abc","def"]
```

## chars(value)

Returns a String array consisting of the characters in the String.

```java
final String title = "title";
char[] result = chars(title);
// result = ["t", "i", "t", "l", "e"]
```


## collapseWhitespace(value)

Replace consecutive whitespace characters with a single space.

```java
final String input = " foo    bar  ";
final String result = collapseWhitespace(title);
// result = "foo bar";
```



## Other functions that can be added

1. head
2. tail