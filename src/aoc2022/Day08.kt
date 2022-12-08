package aoc2022

import utils.readInputAsList
import utils.readInputAsMatrix

fun main() {
    fun part1(input: Array<Array<String>>) : Any {
        for(i in 0 until input.size) {
            for(j in 0 until input[i].size){
                print(input[i][j] + "")
            }
            println()
        }
        return 0
    }

    fun part2(input: List<String>) : Any {
        return 0
    }



    val input = readInputAsMatrix("Day08", "2022")
    println(part1(input))
//    println(part2(input))

    val testInput = readInputAsList("Day08_test", "2022")
//    check(part1(testInput) == 21)
//    check(part2(testInput) == 0)
}