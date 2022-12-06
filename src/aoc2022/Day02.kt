package aoc2022

import utils.readInputAsList

fun main() {

    val part1results = mapOf(
        "A X" to 3+1,
        "A Y" to 6+2,
        "A Z" to 0+3,
        "B X" to 0+1,
        "B Y" to 3+2,
        "B Z" to 6+3,
        "C X" to 6+1,
        "C Y" to 0+2,
        "C Z" to 3+3
    )

    val part2results = mapOf(
        "A X" to 0+3,
        "A Y" to 3+1,
        "A Z" to 6+2,
        "B X" to 0+1,
        "B Y" to 3+2,
        "B Z" to 6+3,
        "C X" to 0+2,
        "C Y" to 3+3,
        "C Z" to 6+1
    )

    fun part1(input : List<String>) : Int {
        var score = 0
        for(round in input) {
            score += part1results[round]!!
        }
        return score
    }

    fun part2(input : List<String>) : Int {
        var score = 0
        for(round in input) {
            score += part2results[round]!!
        }
        return score
    }

    val input = readInputAsList("Day02", "2022")
    println(part1(input))
    println(part2(input))

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsList("Day02_test", "2022")
    check(part1(testInput) == 31)
    check(part2(testInput) == 32)
}