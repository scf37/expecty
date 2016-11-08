![Build status](https://travis-ci.org/scf37/expecty.svg?branch=master)
# Expecty - Power Assertions for Scala

Expecty brings power assertions as known from [Groovy](http://groovy.codehaus.org) and [Spock](http://spockframework.org)
to the [Scala](http://scala-lang.org) language. It is a micro library that aims to do one thing well.

## License

Expecty is licensed under the Apache 2 license.

## Usage

```
resolvers += "Scf37" at "https://dl.bintray.com/scf37/maven/"
libraryDependencies += "me.scf37.expecty" %% "expecty" % "1.0.0"
```

Scala 2.11 and 2.12 compatible.


## Code Examples

```scala
import org.expecty.Expecty

case class Person(name: String = "Fred", age: Int = 42) {
  def say(words: String*) = words.mkString(" ")
}

val person = Person()
val expect = new Expecty()

// Passing expectations

expect {
  person.name == "Fred"
  person.age * 2 == 84
  person.say("Hi", "from", "Expecty!") == "Hi from Expecty!"
}

// Failing expectation

val word1 = "ping"
val word2 = "pong"

expect {
  person.say(word1, word2) == "pong pong"
}

/*
Output:

java.lang.AssertionError:

person.say(word1, word2) == "pong pong"
|      |   |      |      |
|      |   ping   pong   false
|      ping pong
Person(Fred,42)
*/

// Continue despite failing predicate

val expect2 = new Expecty(failEarly = false)

expect2 {
  person.name == "Frog"
  person.age * 2 == 73
}

/*
Output:

java.lang.AssertionError:

person.name == "Frog"
|      |    |
|      Fred false
Person(Fred,42)


person.age * 2 == 73
|      |   |   |
|      42  84  false
Person(Fred,42)
*/
```

## Further Examples

Have a look at [ExpectySpec.scala](https://github.com/scf37/expecty/blob/master/expecty-test/src/test/scala/org/expecty/ExpectySpec.scala)
and other specs in the same directory.


 
