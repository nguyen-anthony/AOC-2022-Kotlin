package aoc2022
import utils.readInputAsList
import kotlin.math.max
fun main() {

    fun isVisibleOutside(map: List<List<Int>>, row: List<Int>, rowIndex: Int, colIndex: Int) : Boolean {
        return rowIndex == 0 || colIndex == 0 || rowIndex == map.lastIndex || colIndex == row.lastIndex
    }

    fun isLeftVisible(row: List<Int>, colIndex: Int, value: Int) : Boolean {
        return row.subList(0, colIndex).all { it < value }
    }

    fun isRightVisible(row: List<Int>, colIndex: Int, value: Int) : Boolean {
        return row.subList(colIndex + 1, row.size).all { it < value }
    }

    fun isUpVisible(map: List<List<Int>>, rowIndex: Int, colIndex: Int, value: Int) : Boolean {
        return map.subList(0, rowIndex).all { it[colIndex] < value }
    }

    fun isDownVisible(map: List<List<Int>>, rowIndex: Int, colIndex: Int, value: Int) : Boolean {
        return map.subList(rowIndex + 1, map.size).all { it[colIndex] < value }
    }

    fun score(map: List<List<Int>>, row: List<Int>, rowIndex: Int, colIndex: Int) : Int {
        return if(rowIndex == 0 || colIndex == 0 || rowIndex == map.lastIndex || colIndex == row.lastIndex) 0 else 1
    }

    fun leftScore(row: List<Int>, colIndex: Int, value: Int) : Int {
        return row.subList(0, colIndex).reversed().fold(Pair(0, false)) { (count, found), newValue ->
            if (found) Pair(count, true)
            else if (newValue >= value) Pair(count + 1, true)
            else Pair(count + 1, false)
        }.first
    }

    fun rightScore(row: List<Int>, colIndex: Int, value: Int) : Int {
        return row.subList(colIndex+1, row.size).fold(Pair(0, false)) { (count, found), newValue ->
            if (found) Pair(count, true)
            else if (newValue >= value) Pair(count + 1, true)
            else Pair(count + 1, false)
        }.first
    }

    fun upScore(map: List<List<Int>>, rowIndex: Int, colIndex: Int, value: Int) : Int {
        return map.subList(0, rowIndex).reversed().fold(Pair(0, false)) { (count, found), list ->
            if(found) Pair(count, true)
            else if (list[colIndex] >= value) Pair(count + 1, true)
            else Pair(count + 1, false)
        }.first
    }

    fun downScore(map: List<List<Int>>, rowIndex: Int, colIndex: Int, value: Int) : Int {
        return map.subList(rowIndex + 1, map.size).fold(Pair(0,false)) { (count, found), list ->
            if(found) Pair(count,true)
            else if (list[colIndex] >= value) Pair(count + 1, true)
            else Pair(count + 1, false)
        }.first
    }

    fun part1(input: List<List<Int>>) : Any {
        return input.withIndex().fold(0) { count, (rowIndex, row) ->
            row.withIndex().fold(count) { rCount, (colIndex, value) ->
                if(isVisibleOutside(input, row, rowIndex, colIndex) ||
                    isLeftVisible(row, colIndex, value) ||
                    isRightVisible(row, colIndex, value) ||
                    isUpVisible(input, rowIndex, colIndex, value) ||
                    isDownVisible(input, rowIndex, colIndex, value)
                ) rCount + 1 else rCount
            }
        }
    }

    fun part2(input: List<List<Int>>) : Any {
        return input.withIndex().fold(0) { max, (rowIndex, row) ->
            row.withIndex().fold(max) { rMax, (colIndex, value) ->
                max(
                    rMax,
                    score(input, row, rowIndex, colIndex) *
                            leftScore(row, colIndex, value) *
                            rightScore(row, colIndex, value) *
                            upScore(input, rowIndex, colIndex, value) *
                            downScore(input, rowIndex, colIndex, value)
                )
            }
        }
    }

    val input = readInputAsList("Day08", "2022").map { line -> line.toList().map { it.digitToInt() } }

    println(part1(input))
    println(part2(input))

    val testInput = readInputAsList("Day08_test", "2022").map { line -> line.toList().map { it.digitToInt() } }
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)
}