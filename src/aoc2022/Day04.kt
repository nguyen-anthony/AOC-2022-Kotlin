package aoc2022

import utils.readInputAsList

fun main() {

    fun part1(input: List<String>) : Int  {
        var fullContains = 0

        for(assignment in input) {
            val (elf1, elf2) = assignment.split(",")
            val (min1, max1) = elf1.split("-").map(String::toInt)
            val (min2, max2) = elf2.split("-").map(String::toInt)

            if(
                min1 <= min2 && max1 >= max2 || min2 <= min1 && max2 >= max1
            ) fullContains++
        }

        return fullContains

    }

    fun part2(input : List<String>) : Int {
        var allContains = 0

        for(assignment in input) {
            val (elf1, elf2) = assignment.split(",")
            val (min1, max1) = elf1.split("-").map(String::toInt)
            val (min2, max2) = elf2.split("-").map(String::toInt)

            val pair1 = min1..max1
            val pair2 = min2..max2

            if(
                pair1.first in pair2 || pair1.last in pair2 || pair2.first in pair1 || pair2.last in pair1
            ) allContains++
        }

        return allContains
    }


    val input = readInputAsList("Day04", "2022")
    println(part1(input))
    println(part2(input))

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsList("Day04_test", "2022")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)
}