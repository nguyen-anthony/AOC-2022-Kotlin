package aoc2022

import utils.readInputAsList

fun main() {

    fun part1(input: List<String>) : MutableList<Int> {
        val sums = mutableListOf<Int>();
        var sum = 0

        for(num in input) {
            if (num.isNotEmpty()){
                sum += num.toInt()
            } else {
                sums.add(sum)
                sum = 0
                continue
            }
        }
        return sums
    }

    fun part2(sums : MutableList<Int>) : Int {
        sums.sort()
        sums.reverse()
        return sums.subList(0,3).sum()
    }

    val input = readInputAsList("Day01", "2022")
    println(part1(input).maxOrNull())
    println(part2(part1(input)))

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsList("Day01_test", "2022")
    check(part1(testInput).maxOrNull() == 100)
    check(part2(part1(testInput)) == 145)
}