package aoc2022

import utils.readInputAsList
data class Step(val point: Pair<Int,Int>, val distance: Int, val parent: Step?)
fun main() {

    fun letter(ch: Char): Char {
        return when (ch) {
            'S' -> 'a'
            'E' -> 'z'
            else -> ch
        }
    }

    fun findShortest(start: Pair<Int,Int>, map: List<List<Char>>): Int {
        val queue = ArrayDeque<Step>()
        val visited = mutableSetOf(start)
        queue.addFirst(Step(start, 0, null))
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (map[node.point.first][node.point.second] == 'E') {
                return node.distance
            }
            setOf(
                if (node.point.second < map[node.point.first].size - 1) node.point.first to node.point.second + 1 else null,
                if (node.point.second > 0) node.point.first to node.point.second - 1 else null,
                if (node.point.first > 0) node.point.first - 1 to node.point.second else null,
                if (node.point.first < map.size - 1) node.point.first + 1 to node.point.second else null)
                .filterNotNull()
                .filter {letter(map[it.first][it.second]) - letter(map[node.point.first][node.point.second]) <= 1 }
                .filter { !visited.contains(it) }
                .forEach {
                    visited.add(it)
                    queue.add(Step(it, node.distance + 1, node))
                }
        }
        return Int.MAX_VALUE
    }

    fun part1(input: List<String>): Int {
        val map = input.map{ it.toList() }
        lateinit var starts : Pair<Int,Int>
        for(i in map.indices) {
            for (j in 0 until map[i].size) {
                if (map[i][j] == 'S') {
                    starts = i to j
                }
            }
        }

        return findShortest(starts, map)
    }

    fun part2(input: List<String>): Int {
        val map = input.map{ it.toList() }
        var starts = mutableListOf<Pair<Int,Int>>()

        for(i in map.indices) {
            for (j in 0 until map[i].size) {
                if (map[i][j] == 'S' || map[i][j] == 'a') {
                    starts.add(i to j)
                }
            }
        }
        return starts.minOf { findShortest(it, map) }
    }

    val input = readInputAsList("Day12", "2022")
    println(part1(input))
    println(part2(input))

//    val testInput = readInputAsList("Day12_test", "2022")
//    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)
}