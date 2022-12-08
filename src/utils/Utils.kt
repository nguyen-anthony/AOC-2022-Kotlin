package utils

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.math.BigInteger
import java.security.MessageDigest


val alphaToIntMap = ('a'..'z').zip(1..26).plus(('A'..'Z').zip(27..52)).associate { it.first to it.second }

/**
 * Reads lines from the given input txt file.
 */
fun readInputAsList(name: String, year: String) = File("src/aoc$year/resources", "$name.txt").readLines()

fun readInputAsString(name: String, year: String) = File("src/aoc$year/resources", "$name.txt").readText()

fun readInputAsMatrix(name: String, year: String) : Array<Array<String>>{
    val file = File("src/aoc$year/resources", "$name.txt")
    val reader = BufferedReader(file.reader())

    val line = reader.readLine()
    val words = line.split("")
    val twoDArray = Array(file.length().toInt()) { Array(words.size) { "" } }
    for( i in 0 until file.length().toInt()) {
        for(j in 0 until words.size) {
            twoDArray[i][j] = words[j]
        }
    }
    reader.close()
    return twoDArray
}

/**
 * Converts string to utils.md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun alphaToInt(character: Char) : Int? {
    return alphaToIntMap[character]
}