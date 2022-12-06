package aoc2022

import utils.readInputAsString

fun main() {

    fun getAnswer(input : String, windowSize : Int) : Int{
        val windowed = input.windowed(windowSize)
        var answer = 0
        for ((index, value) in windowed.withIndex()) {
            if(value.allUnique()) {
                answer = index + windowSize
                break
            }
        }
        return answer
    }

    fun part1(input: String) : Int {
        return getAnswer(input, 4)
    }

    fun part2(input: String) : Int {
        return getAnswer(input, 14)
    }

    val input = readInputAsString("Day06", "2022")
    println(part1(input))
    println(part2(input))

    val testInput = readInputAsString("Day06_test", "2022")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)
}

fun String.allUnique(): Boolean = all(hashSetOf<Char>()::add)