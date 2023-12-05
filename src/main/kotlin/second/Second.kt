package second

import util.FileReader

class Second(fileName: String) {
    private val gameList = FileReader.readFileLineByLineToAListOfStrings(fileName)

    companion object {
        val redRegex = "(\\d+)\\s+red".toRegex()
        val greenRegex = "(\\d+)\\s+green".toRegex()
        val blueRegex = "(\\d+)\\s+blue".toRegex()

        val redCubes = 12;
        val greenCubes = 13;
        val blueCubes = 14;
    }

    fun firstPartSolver(): Int {
        var possibleGameIDsSum = 0;

        for (rows in gameList) {
            var isValueOver = false;
            val gameID = Integer.parseInt(rows.split(":").get(0).split(" ").get(1));
            val secondPart = rows.split(":").get(1)
            for (match in secondPart.split(";")) {
                if (redCubes - findValues(redRegex.findAll(match)) < 0
                    || blueCubes - findValues(blueRegex.findAll(match)) < 0
                    || greenCubes - findValues(greenRegex.findAll(match)) < 0
                ) {
                    isValueOver = true;
                    break;
                }
            }
            if (!isValueOver) {
                possibleGameIDsSum += gameID;
            }
        }
        return possibleGameIDsSum;
    }

    fun findValues(row: Sequence<MatchResult>): Int {
        return row.flatMap { result ->
            result.groupValues[1].split(",").flatMap { it.split("\\s+").mapNotNull { it.toIntOrNull() } }
        }.sum()
    }
}

fun main() {
    val second = Second("src/main/resources/gameList_real.txt")
    println(second.firstPartSolver())
}