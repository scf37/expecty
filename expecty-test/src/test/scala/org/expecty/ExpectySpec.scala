/*
* Copyright 2012 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*     http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.expecty

import org.scalatest.FunSuite

class ExpectySpec extends FunSuite {
  val expect = new Expecty()
  val name = "Hi from Expecty!"

  test("passingExpectation") {
    expect(name.length == 16)
  }

  test("failingExpectation") {
    intercept[AssertionError] {
      expect(name.length() == 10)
    }
  }

  test("multiplePassingExpectations") {
    expect(name.length == 16)
    expect(name.startsWith("Hi"))
    expect(name.endsWith("Expecty!"))
  }

  test("mixedPassingAndFailingExpectations") {
    expect(name.length == 16)
    intercept[AssertionError] {
      expect(name.startsWith("Ho"))
    }
    expect(name.endsWith("Expecty!"))
  }

  test("passingMultiExpectation") {
    expect {
      name.length == 16
      name.startsWith("Hi")
      name.endsWith("Expecty!")
    }
  }

  test("failingMultiExpectation") {
    intercept[AssertionError] {
      expect {
        name.length == 16
        name.startsWith("Ho")
        name.endsWith("Expecty!")
      }
    }
  }

  test("lateFailingExpectation") {
    def expect = new Expecty(failEarly = false)

    intercept[AssertionError] {
      expect {
        name.length == 13
        name.startsWith("Ho")
        name.endsWith("Expcty!")
      }
    }
  }
}
