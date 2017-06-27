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

private val DEFAULT_INITIAL_ODD_NUMBER = 17
private val DEFAULT_MULTIPLIER_PRIME = 37

/**
 * Generates a hashcode for given fields
 * @param fields Fields to generate a hashcode from
 * @param initialOddNumber Odd number to start with
 * @param multiplierPrime Prime number to multiply hashes with
 * @return Hashcode
 * @see Any.hashCode
 */
fun hashKode(vararg fields: Any?, initialOddNumber: Int = 17, multiplierPrime: Int = 37): Int
{
    if (HashKode.VERIFY_HASHKODE_PARAMETERS ||
            initialOddNumber != DEFAULT_INITIAL_ODD_NUMBER ||
            multiplierPrime != DEFAULT_MULTIPLIER_PRIME)
    {
        require(initialOddNumber.isOdd()) { "InitialOddNumber must be an odd number" }
        require(multiplierPrime.isPrime()) { "MultiplierPrime must be a prime number" }
    }

    var result = initialOddNumber
    fields
            .forEach { field ->
                val hash = field?.hashCode() ?: 0
                result = multiplierPrime * result + hash
            }
    return result
}

private fun Int.isOdd() = this % 2 != 0
private fun Int.isPrime()= this > 1 && (2..(this / 2)).all { this % it != 0 }