package aoc2022

import utils.readInputAsList
import kotlin.math.abs
import kotlin.math.sign

fun main() {

    data class Point(val x: Int, val y: Int)

    fun Point.isNear(other: Point) : Boolean {
        return   (x == other.x && y == other.y) ||
                (x == other.x + 1 && y == other.y) ||
                (x == other.x - 1 && y == other.y) ||

                (x == other.x && y == other.y + 1) ||
                (x == other.x && y == other.y - 1) ||

                (x == other.x - 1 && y == other.y - 1) ||
                (x == other.x + 1 && y == other.y + 1) ||
                (x == other.x - 1 && y == other.y + 1) ||
                (x == other.x + 1 && y == other.y - 1)

    }

    fun part1(input: List<String>) : Any {
        val points = mutableListOf<Point>()
        points.add(Point(0, 0))

        var head = Point(0, 0)
        var tail = Point(0, 0)
        for(line in input) {
            val (direction, count) = line.split(" ")
            repeat(count.toInt()) {
                when(direction) {
                    "R" -> {
                        head = head.copy(x = head.x + 1)
                        if (!tail.isNear(head)) tail = head.copy(x = head.x - 1)
                    }
                    "L" -> {
                        head = head.copy(x = head.x - 1)
                        if (!tail.isNear(head)) tail = head.copy(x = head.x + 1)
                    }
                    "U" -> {
                        head = head.copy(y = head.y - 1)
                        if (!tail.isNear(head)) tail = head.copy(y = head.y + 1)
                    }
                    "D" -> {
                        head = head.copy(y = head.y + 1)
                        if (!tail.isNear(head)) tail = head.copy(y = head.y - 1)
                    }
                }

                points.add(tail)
            }
        }
        return points.distinct().size
    }

    fun part2(input: List<String>) : Any {
        val rope = Array(10) { 0 to 0 }
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(rope.last())

        for(line in input){
            val (direction, count) = line.split(" ")
            val step = when(direction) {
                "R" -> 1 to 0
                "L" -> -1 to 0
                "U" -> 0 to 1
                "D" -> 0 to -1
                else -> error("you're never going to error")
            }

            repeat(count.toInt()){
                rope[0] = rope[0].first + step.first to rope[0].second + step.second
                for(i in 0 until rope.lastIndex) {
                    val head = rope[i]
                    val tail = rope[i+1]

                    rope[i+1] = if(abs(head.first - tail.first) > 1 || abs(head.second - tail.second) > 1) {
                        val tailX = if (head.first == tail.first) 0 else (head.first - tail.first).sign
                        val tailY = if (head.second == tail.second) 0 else (head.second - tail.second).sign
                        tail.first + tailX to tail.second + tailY
                    } else tail
                }
                visited += rope.last()
            }
        }

        return visited.size
    }



    val input = readInputAsList("Day09", "2022")
    println(part1(input))
    println(part2(input))

    val testInput = readInputAsList("Day09_test", "2022")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)
}

