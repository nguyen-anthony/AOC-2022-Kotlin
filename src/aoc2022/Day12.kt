package aoc2022

import utils.readFileTo2DArray
import utils.readInputAsList
import utils.readInputAsMatrix

fun main() {

    fun getStartAndEnd(input: Array<Array<String>>) : Pair<Any, Any> {
        lateinit var start : Pair<Int, Int>
        lateinit var end : Pair<Int, Int>
        // Iterate over the rows of the array
        for (i in input.indices) {
            // Iterate over the columns of the current row
            for (j in input[i].indices) {
                if(input[i][j] == "S"){
                    start = Pair(i, j)
                }
                if(input[i][j] == "E"){
                    end = Pair(i, j)
                }
            }

        }
        return Pair(start, end)
    }

    fun part1(input: Array<Array<String>>) : Any {
        var (start, end) = getStartAndEnd(input)

//        println(start)
//        println(end)
//        println(input[start.first][start.second])
//        println(input[end.first][end.second])
        return 0
    }

    fun part2(input: List<String>) : Any {
        return 0
    }



    val input = readFileTo2DArray("Day12", "2022")
    println(part1(input))
//    println(part2(input))

//    val testInput = readInputAsList("Day12_test", "2022")
//    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)
}