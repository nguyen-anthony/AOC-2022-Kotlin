package aoc2022

import utils.readInputAsList

fun main() {
    fun part1(input: List<String>) : Any {
        return 0
    }

    fun part2(input: List<String>) : Any {
        return 0
    }



    val input = readInputAsList("Day11", "2022")
    println(part1(input))
    println(part2(input))

    val testInput = readInputAsList("Day11_test", "2022")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)
}