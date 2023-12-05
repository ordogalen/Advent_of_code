package second

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SecondTest{

    @Test
    fun test_First(){
        val second = Second("src/main/resources/gameList_test.txt")
        assertEquals(8, second.firstPartSolver())
    }

    @Test
    fun test_Second(){
        val second = Second("src/main/resources/gameList_test.txt")
        assertEquals(2286, second.secondPartSolver())
    }
}