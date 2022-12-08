package aoc2022

import utils.readInputAsList

data class FileInfo(val fileSize: Int, val name: String, val isDirectory: Boolean)

fun main() {
    fun parseDirectories(input: List<String>): Map<String, List<FileInfo>> {
        var path = listOf("/")
        val map = mutableMapOf<String, List<FileInfo>>()

        for(terminalLine in input){
            if (terminalLine == "$ cd /") {
                path = listOf("/")
            } else if (terminalLine == "$ cd ..") {
                path = path.dropLast(1)
            } else if (terminalLine.startsWith("$ cd")) {
                val newDir = terminalLine.split(" ").last()
                path = (path + newDir)
            } else if (terminalLine == "$ ls") {
                continue
            } else if (terminalLine.startsWith("dir")) {
                val pathString = path.joinToString("/")
                val name = terminalLine.split(" ").last()
                val file = FileInfo(0, name, true)
                val currentList = map.getOrDefault(pathString, emptyList())
                map[pathString] = currentList + file
            } else {
                val pathString = path.joinToString("/")
                val (size, name) = terminalLine.split(" ")
                val file = FileInfo(size.toInt(), name, false)
                val currentList = map.getOrDefault(pathString, emptyList())
                map[pathString] = currentList + file
            }
        }
        return map
    }

    fun getSize(dirMap: Map<String, List<FileInfo>>, dir: String) : Int {
        val files = dirMap.getValue(dir)
        val fileSize = files.filter { !it.isDirectory }.sumOf { it.fileSize }
        val subDirectories = files.filter { it.isDirectory }
        return fileSize + subDirectories.sumOf { getSize(dirMap, dir + "/" + it.name) }
    }

    fun getSizes(dirMap: Map<String, List<FileInfo>>) : Map<String, Int> {
        return dirMap.mapValues { entry -> getSize(dirMap, entry.key) }
    }

    fun part1(input: List<String>) : Any {
        return getSizes(parseDirectories((input))).filter { it.value <= 100000 }.values.sum()
    }

    fun part2(input: List<String>) : Int {
        return getSizes(parseDirectories(input)).values.filter { it > (30000000 - (70000000 - getSizes(parseDirectories(input)).getValue("/"))) }.min()
    }



    val input = readInputAsList("Day07", "2022")
    println(part1(input))
    println(part2(input))

    val testInput = readInputAsList("Day07_test", "2022")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)
}