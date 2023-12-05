package second

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SecondTest{

    @Test
    fun test_First(){
        val second = Second("src/main/resources/gameList_test.txt")
        println(second.firstPartSolver())
    }
}