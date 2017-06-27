/*
 * MIT License
 *
 * Copyright (c) 2017 Pim van den Berg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package nl.pvdberg.hashkode

import io.kotlintest.matchers.lt
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec

class HashingTest : StringSpec()
{
    init
    {
        "Default arguments do not fail" {
            hashKode()
        }

        "Even initial number fails" {
            shouldThrow<IllegalArgumentException> {
                hashKode(initialOddNumber = 4)
            }
        }

        "Non prime number fails" {
            shouldThrow<IllegalArgumentException> {
                hashKode(multiplierPrime = 4)
            }
        }

        "Hash is unique" {
            hashKode("Test", 1, 2, 3) shouldNotBe hashKode(1, 2, 3, "Test")
            hashKode(null, Any(), Any()) shouldNotBe hashKode(55)
            hashKode(Any(), null) shouldNotBe hashKode(null, Any())
            hashKode(Any()) shouldNotBe hashKode(Any())
        }

        "Hash is consistent" {
            val obj = Any()
            hashKode(obj) shouldBe hashKode(obj)
            hashKode(1, 2, 3) shouldBe hashKode(1, 2, 3)
            hashKode("Test") shouldBe hashKode("Test")
        }

        "Hash can overflow" {
            hashKode(Integer.MAX_VALUE) shouldBe lt(0)
            hashKode(Integer.MAX_VALUE) shouldBe hashKode(Integer.MAX_VALUE)
        }
    }
}