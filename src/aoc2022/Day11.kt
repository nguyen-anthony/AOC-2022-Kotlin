package aoc2022

import utils.readInputAsList


data class Monkey(
    val items: MutableList<Long>,
    val op: String,
    val opVal: Long?,
    val test: Int,
    val ifTrue: Int,
    val ifFalse: Int,
    var inspections: Long = 0L
)

fun main() {

    fun monkeys(input: List<String>): List<Monkey> {
        return input.windowed(6, 7).map { line ->
            Monkey(
                items = line[1].drop("  Starting items: ".length).split(", ").map { it.toLong() }.toMutableList(),
                op = if (line[2].contains("*")) "multiply" else "add",
                opVal = line[2].split(" ").last().toLongOrNull(),
                test = line[3].split(" ").last().toInt(),
                ifTrue = line[4].split(" ").last().toInt(),
                ifFalse = line[5].split(" ").last().toInt(),
            )
        }
    }

    fun part1(input: List<String>) : Long {
        val monkeys = monkeys(input)

        repeat(20) {
            for(monkey in monkeys){
                for(item in monkey.items.toList()){
                    val newItemWorry = if (monkey.op == "add") {
                        item + (monkey.opVal ?: item)
                    } else {
                        item * (monkey.opVal ?: item)
                    } / 3

                    if (newItemWorry % monkey.test == 0L) {
                        monkeys[monkey.ifTrue].items.add(newItemWorry)
                    } else {
                        monkeys[monkey.ifFalse].items.add(newItemWorry)
                    }
                    monkey.inspections++
                }
                monkey.items.clear()
            }
        }

        return monkeys.map { it.inspections}.maxOf{ it } * monkeys.map {it.inspections}.sortedDescending()[1]
    }

    fun part2(input: List<String>): Long {
        val monkeys = monkeys(input)
        val commonMultiple = monkeys.map { it.test }.fold(1L) { x, y -> x * y }

        repeat(10000) {
            for(monkey in monkeys){
                for(item in monkey.items.toList()){
                    val newItemWorry = if (monkey.op == "add") {
                        item + (monkey.opVal ?: item)
                    } else {
                        item * (monkey.opVal ?: item)
                    } % commonMultiple

                    if (newItemWorry % (monkey.test.toLong()) == 0L) {
                        monkeys[monkey.ifTrue].items.add(newItemWorry)
                    } else {
                        monkeys[monkey.ifFalse].items.add(newItemWorry)
                    }
                    monkey.inspections++
                }
                monkey.items.clear()
            }
        }
        return monkeys.map { it.inspections}.maxOf{ it } * monkeys.map {it.inspections}.sortedDescending()[1]
    }


    val input = readInputAsList("Day11", "2022")
    println(part1(input))
    println(part2(input))
}