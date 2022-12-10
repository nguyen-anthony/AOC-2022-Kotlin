package aoc2022

import utils.readInputAsList
import utils.readInputAsString

fun main() {

    fun part1(input: List<String>) : Any {
        var regVal = 1
        var cycleCount = 1
        var sum = 0

        fun runCycle() {
            if (cycleCount % 40 == 20) sum += cycleCount * regVal
            cycleCount++
        }

        for(line in input){
            if (line.startsWith("noop")) runCycle()
            else {
                runCycle(); runCycle()
                regVal += line.split(" ").last().toInt()
            }
        }

        return sum
    }

    fun part2(input: List<String>) {
        var x = 1
        var cycleCount = 1
        val cycleX = mutableListOf<Int>()

        fun runCycle() {
            cycleCount++
            cycleX.add(x)
        }

        for(line in input) {
            if (line.startsWith("noop")) runCycle()
            else {
                runCycle()
                runCycle()
                x += line.split(" ").last().toInt()
            }
            cycleX.forEachIndexed{i, it ->
                val pos = i % 40
                if(it - 1 == pos || it == pos || it + 1 == pos){
                    print('#')
                } else print(' ')
                if(i %40 == 39 ) {
                    println()
                }
            }
        }
    }



    val input = readInputAsList("Day10", "2022")
    println(part1(input))
    part2(input)

//    val testInput = readInputAsList("Day10_test", "2022")
//    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)
}