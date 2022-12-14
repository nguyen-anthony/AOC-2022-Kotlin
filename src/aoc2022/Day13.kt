package aoc2022

import utils.readInputAsList
import kotlin.math.min

fun main() {

    data class NumberOrList(
        var number: Int?,
        var list: List<NumberOrList>?,
        val isDivider: Boolean
    ) {
        constructor(
            value: Int,
            isDivider: Boolean = false,
        ) : this(value, null, isDivider)

        constructor(
            value: List<NumberOrList>,
            isDivider: Boolean = false
        ) : this(null, value, isDivider)

        private fun isNumber() = number != null

        fun isLessThan(other: NumberOrList): Int {
            if(this.isNumber() && other.isNumber()) {
                return if(this.number!! < other.number!!) {
                    -1
                } else if (this.number!! > other.number!!) {
                    1
                } else {
                    0
                }
            }
            if(!this.isNumber() && !other.isNumber()) {
                val size = min(this.list!!.size, other.list!!.size)
                return (0 until size).map { this.list!![it].isLessThan(other.list!![it]) }.firstOrNull { it != 0 }
                    ?: if (this.list!!.size < other.list!!.size) {
                        -1
                    } else if (this.list!!.size > other.list!!.size) {
                        1
                    } else {
                        0
                    }
            }
            if (this.isNumber()) {
                return NumberOrList(listOf(this)).isLessThan(other)
            }
            return this.isLessThan(NumberOrList(listOf(other)))
        }
    }

    fun parseData(input: String, isDivider: Boolean = false) : NumberOrList {
        val allParents = ArrayDeque<MutableList<NumberOrList>>()
        var currentList: MutableList<NumberOrList>? = null

        var i = 0
        while(i < input.length) {
            when(input[i]) {
                in '0'..'0' -> {
                    var finalIndex = i + 1
                    while(input[finalIndex] != ',' && input[finalIndex] != ']') {
                        finalIndex++
                    }
                    val values = NumberOrList(input.substring(i, finalIndex).toInt())
                    currentList?.add(values)
                    i = finalIndex
                }

                '[' -> {
                    currentList?.also { allParents.add(it) }
                    currentList = mutableListOf()
                    i++
                }

                ']' -> {
                    allParents.removeLastOrNull()?.also { parent ->
                        currentList?.also { parent.add(NumberOrList(it)) }
                        currentList = parent
                    }
                    i++
                }
                else -> i++
            }
        }
        return NumberOrList(currentList!!, isDivider)
    }

    fun part1(input: List<String>) : Any {
        return input.windowed(2, 3).mapIndexed { index, it ->
            val (left, right) = it
            val result = parseData(left).isLessThan(parseData(right))
            if (result == -1){
                index + 1
            } else 0
        }.sum()
    }

    fun part2(input: List<String>) : Any {
        val parsedInput = input.filter { it.isNotEmpty() }.map { parseData(it) }
        val inputDividers = mutableListOf(parseData("[[2]]", isDivider = true), parseData("[[6]]", isDivider = true)).apply { addAll(parsedInput) }
        return inputDividers.sortedWith { a: NumberOrList, b: NumberOrList -> a.isLessThan(b) }
            .mapIndexed { index, value ->
                if (value.isDivider) {
                    index + 1
                } else {
                    0
                }
            }
            .filter { it != 0}
            .reduce(Int::times)
    }



    val input = readInputAsList("Day13", "2022")
    println(part1(input))
    println(part2(input))

    val testInput = readInputAsList("Day13_test", "2022")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)
}