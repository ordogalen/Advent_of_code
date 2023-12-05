package first

import util.FileReader

class First(fileName: String) {
    private val calibrationValues = FileReader.readFileLineByLineToAListOfStrings(fileName)

    companion object {
        val wordToNumber = mapOf(
            "one" to "o1e", "two" to "t2o", "three" to "t3e", "four" to "f4r",
            "five" to "f5e", "six" to "s6x", "seven" to "s7n", "eight" to "e8t", "nine" to "n9e"
        )
    }

    fun firstPartSolver(values: List<String> = calibrationValues): Int {
        return values.sumOf { row ->
            val firstDigit = row.firstOrNull { it.isDigit() }?.toString() ?: ""
            val reversedFirstDigit = row.reversed().firstOrNull { it.isDigit() }?.toString() ?: ""
            "$firstDigit$reversedFirstDigit".toIntOrNull() ?: 0
        }
    }

    fun secondPartSolver(): Int {
        val modifiedRows = mutableListOf<String>()

        for (row in calibrationValues) {
            val regexPattern = wordToNumber.keys.joinToString("|").toRegex()

            val replacedString = getReplacedString(regexPattern, row)
            // ugly dont care anymore
            val replacedStringAgainxd = getReplacedString(regexPattern, replacedString)
            modifiedRows.add(replacedStringAgainxd)
        }
        return firstPartSolver(modifiedRows)
    }

    private fun getReplacedString(
        regexPattern: Regex,
        row: String
    ) = regexPattern.replace(row) {
        wordToNumber[it.value] ?: it.value
    }


}