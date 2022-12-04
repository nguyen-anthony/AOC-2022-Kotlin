package aoc2022

import utils.readInput
import utils.alphaToInt

fun main() {

    fun part1(input : List<String>) : Int {
        var sum = 0

        for(rucksack in input) {
            val first = rucksack.substring(0, rucksack.length / 2)
            val second = rucksack.substring(rucksack.length / 2)
            //just initializes character as a non-empty value
            var commonChar = 'a'
            for(char in first){
                if(second.contains(char)){
                    commonChar = char
                }
            }

            sum += alphaToInt(commonChar)!!
        }
        return sum
    }

    fun part2(input : List<String>) : Int {
        var sum = 0
        for(group in input.windowed(3,3)){
            //just initializes character as a non-empty value
            var commonChar = 'a'
            for(char in group[0]){
                if(group[1].contains(char) && group[2].contains(char)){
                    commonChar = char
                }
            }
            sum += alphaToInt(commonChar)!!
        }
        return sum
    }

    val input = readInput("Day03", "2022")
    println(part1(input))
    println(part2(input))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test", "2022")
    check(part1(testInput) == 77)
    check(part2(testInput) == 16)
}