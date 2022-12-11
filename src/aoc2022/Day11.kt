package aoc2022

import utils.readInputAsList

data class Monkey(
    val items: MutableList<Int>,
    val op: String,
    val opValue: Int?,
    val test: Int,
    val ifTrue: Int,
    val ifFalse: Int,
    var inspections: Int = 0
)

fun main() {

    fun getInputs(input: List<String>): List<Monkey> {
        return input.windowed(7, 7).map { monkey ->
            Monkey(
                monkey[1].drop("  Starting items: ".length).split(", ").map { it.toInt() }.toMutableList(),
                if(monkey[2].contains("*")) "multiply" else "add",
                monkey[2].split(" ").last().toIntOrNull(),
                monkey[3].split(" ").last().toInt(),
                monkey[4].split(" ").last().toInt(),
                monkey[5].split(" ").last().toInt(),
            )
        }
    }

    fun part1(input: List<String>) : Any {
        println(getInputs(input))
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