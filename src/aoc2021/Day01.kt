package aoc2021

import utils.readInput

fun main() {

    fun convertToInt(stringList: List<String>) : List<Int> {
        return stringList.map { it.toInt() }
    }

    fun part1(input: List<String>): Int {
        return convertToInt(input).zipWithNext().count { (a, b) -> a < b }
    }

    fun part2(input: List<String>): List<Int> {
        return input.map { it.toInt() }.windowed(3).map { it.sum() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test", "2021")
    check(part1(testInput) == 7)
    check(part1(part2(testInput).map { it.toString() }) == 5)

    val input = readInput("Day01", "2021")
    println(part1(input))
    println(part1(part2(input).map { it.toString() }))
}

