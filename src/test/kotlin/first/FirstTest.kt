package first

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FirstTest{

    @Test
    fun test_Solver(){
        val first = First("src/main/resources/calibrationValues_test.txt")
        assertEquals(142, first.firstPartSolver())
    }

    @Test
    fun test_SecondPartSolver(){
        val first = First("src/main/resources/calibrationValues_test_2.txt")
        assertEquals(281, first.secondPartSolver())
    }
}