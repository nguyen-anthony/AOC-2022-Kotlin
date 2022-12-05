package aoc2022

import utils.readInput

fun main() {

    fun part1(input: List<String>) : String {
        //manual create of each stack
        val stack1 = ArrayDeque(mutableListOf("R","G","J","B","T","V","T"))
        val stack2 = ArrayDeque(mutableListOf<String>("J","R","V","L"))
        val stack3 = ArrayDeque(mutableListOf<String>("S","Q","F"))
        val stack4 = ArrayDeque(mutableListOf<String>("Z","H","N","L","F","V","Q","G"))
        val stack5 = ArrayDeque(mutableListOf<String>("R","Q","T","J","C","S","M","W"))
        val stack6 = ArrayDeque(mutableListOf<String>("S","W","T","C","H","F"))
        val stack7 = ArrayDeque(mutableListOf<String>("D","Z","C","V","F","N","J"))
        val stack8 = ArrayDeque(mutableListOf<String>("L","G","Z","D","W","R","F","Q"))
        val stack9 = ArrayDeque(mutableListOf<String>("J","B","W","V","P"))
        val allStacks = mutableListOf(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9)

        for(line in input) {
            //removed "move", "from", and "to" so each line is just three numbers
            var (numMoves, fromStack, toStack) = line.split(" ").map(String::toInt)
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
        val stack1 = ArrayDeque(mutableListOf("R","G","J","B","T","V","T"))
        val stack2 = ArrayDeque(mutableListOf<String>("J","R","V","L"))
        val stack3 = ArrayDeque(mutableListOf<String>("S","Q","F"))
        val stack4 = ArrayDeque(mutableListOf<String>("Z","H","N","L","F","V","Q","G"))
        val stack5 = ArrayDeque(mutableListOf<String>("R","Q","T","J","C","S","M","W"))
        val stack6 = ArrayDeque(mutableListOf<String>("S","W","T","C","H","F"))
        val stack7 = ArrayDeque(mutableListOf<String>("D","Z","C","V","F","N","J"))
        val stack8 = ArrayDeque(mutableListOf<String>("L","G","Z","D","W","R","F","Q"))
        val stack9 = ArrayDeque(mutableListOf<String>("J","B","W","V","P"))
        val allStacks = mutableListOf(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9)

        for(line in input) {
            var (numBoxes, fromStack, toStack) = line.split(" ").map(String::toInt)
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
//    check(part1(testInput) == 2)
//    check(part2(testInput) == 4)
}