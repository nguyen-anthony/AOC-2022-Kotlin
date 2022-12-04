package utils

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest


val alphaToIntMap = ('a'..'z').zip(1..26).plus(('A'..'Z').zip(27..52)).associate { it.first to it.second }

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, year: String) = File("src/aoc$year/resources", "$name.txt").readLines()

/**
 * Converts string to utils.md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun alphaToInt(character: Char) : Int? {
    return alphaToIntMap[character]
}