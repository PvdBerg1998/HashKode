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

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class DifferenceTest : StringSpec()
{
    init
    {
        "Difference check functions as predicted" {
            val tester1 = BasicTester(f1 = "Hello")
            val tester2 = BasicTester(f1 = "World")

            val diff = tester1.getDifferences(tester2)
            {
                compareField(BasicTester::f1)
            }

            diff.size shouldBe 1
            with (diff.first())
            {
                val (owner1, field1) = this.field1
                (owner1 === tester1) shouldBe true
                field1 shouldBe "Hello"

                val (owner2, field2) = this.field2
                (owner2 === tester2) shouldBe true
                field2 shouldBe "World"
            }
        }
    }
}