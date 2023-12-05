package util

import java.io.File

class FileReader {

    companion object{
        fun readFileLineByLineToAListOfStrings(fileName: String): List<String> {
            val items = arrayListOf<String>()
            File(fileName).forEachLine { e -> items.add(e) }
            return items
        }
    }
}