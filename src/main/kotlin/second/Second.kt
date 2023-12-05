package second

import util.FileReader
import java.lang.Integer.max

class Second(fileName: String) {
    private val gameList = FileReader.readFileLineByLineToAListOfStrings(fileName)

    companion object {
        private val redRegex = "(\\d+)\\s+red".toRegex()
        private val greenRegex = "(\\d+)\\s+green".toRegex()
        private val blueRegex = "(\\d+)\\s+blue".toRegex()

        private const val redCubes = 12
        private const val greenCubes = 13
        private const val blueCubes = 14
    }

    fun firstPartSolver(): Int {
        var possibleGameIDsSum = 0

        for (rows in gameList) {
            val isValueOver = rows.split(":")[1].split(";").any { isValuesOverLimit(it) }

            if (!isValueOver) {
                possibleGameIDsSum += rows.split(":")[0].split(" ")[1].toInt()
            }
        }
        return possibleGameIDsSum
    }

    fun secondPartSolver(): Int {
        var sumVal = 0;
        for (rows in gameList) {
            val secondPart = rows.split(":").get(1)

            val colorValues = processColorValues(secondPart)

            sumVal += colorValues.blue*colorValues.green*colorValues.red
        }
        return sumVal;
    }
    data class ColorValues(var blue: Int = 0, var red: Int = 0, var green: Int = 0)
    private fun processColorValues(input: String): ColorValues {
        val colorValues = ColorValues()

        for (match in input.split(";")) {
            colorValues.blue = max(colorValues.blue, findValues(blueRegex.findAll(match)))
            colorValues.red = max(colorValues.red, findValues(redRegex.findAll(match)))
            colorValues.green = max(colorValues.green, findValues(greenRegex.findAll(match)))
        }

        return colorValues
    }


    private fun isValuesOverLimit(match: String): Boolean {
        return redCubes - findValues(redRegex.findAll(match)) < 0 ||
                blueCubes - findValues(blueRegex.findAll(match)) < 0 ||
                greenCubes - findValues(greenRegex.findAll(match)) < 0
    }

    private fun findValues(row: Sequence<MatchResult>): Int {
        return row.flatMap { result ->
            result.groupValues[1].split(",").flatMap { it.split("\\s+").mapNotNull { it.toIntOrNull() } }
        }.sum()
    }
}

fun main() {
    val second = Second("src/main/resources/gameList_real.txt")
    println(second.secondPartSolver())
}