package aoc2022

import utils.readInput

fun main() {

    fun part1(input: List<String>) : String {
        val split = input.indexOf("")
        val stacks = input.slice(0 until split)
        val instructionsList = input.slice(split+1 until input.size)
        val allStacks = mutableListOf<ArrayDeque<String>>()
        for(stack in stacks) {
            val crates = stack.chunked(1)
            allStacks.add(ArrayDeque(crates))
        }

        for(line in instructionsList) {
            val instructions = line.split(" ")
            var numMoves = instructions[1].toInt()
            val fromStack = instructions[3].toInt()
            val toStack = instructions[5].toInt()
            while(numMoves > 0){
                allStacks[toStack-1].add(allStacks[fromStack-1].removeLast())
                numMoves--
            }
        }

        var topOfAllStacks = ""

        for(stack in allStacks){
            topOfAllStacks += stack.last()
        }

        return topOfAllStacks
    }

    fun part2(input : List<String>) : String {
        val split = input.indexOf("")
        val stacks = input.slice(0 until split)
        val instructionsList = input.slice(split+1 until input.size)
        val allStacks = mutableListOf<ArrayDeque<String>>()
        for(stack in stacks) {
            val crates = stack.chunked(1)
            allStacks.add(ArrayDeque(crates))
        }

        for(line in instructionsList) {
            val instructions = line.split(" ")
            var numBoxes = instructions[1].toInt()
            val fromStack = instructions[3].toInt()
            val toStack = instructions[5].toInt()
            val fromStackList = allStacks[fromStack-1]
            val cratesToMove = fromStackList.slice((fromStackList.size - numBoxes) until fromStackList.size)

            allStacks[toStack-1].addAll(cratesToMove)

            while(numBoxes > 0){
                allStacks[fromStack-1].removeLast()
                numBoxes--
            }

        }
        var topOfAllStacks = ""

        for(stack in allStacks){
            topOfAllStacks += stack.last()
        }

        return topOfAllStacks
    }


    val input = readInput("Day05", "2022")
    println(part1(input))
    println(part2(input))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test", "2022")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")
}
