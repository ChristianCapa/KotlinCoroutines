package main

import kotlinx.coroutines.*

fun main(args: Array<String>) {

    val sudoku = Sudoku()

// easy
/*
    sudoku.setField(1, 1, 5)
    sudoku.setField(1, 2, 6)
    sudoku.setField(1, 4, 8)
    sudoku.setField(1, 5, 4)
    sudoku.setField(1, 6, 7)
    sudoku.setField(2, 1, 3)
    sudoku.setField(2, 3, 9)
    sudoku.setField(2, 7, 6)
    sudoku.setField(3, 3, 8)
    sudoku.setField(4, 2, 1)
    sudoku.setField(4, 5, 8)
    sudoku.setField(4, 8, 4)
    sudoku.setField(5, 1, 7)
    sudoku.setField(5, 2, 9)
    sudoku.setField(5, 4, 6)
    sudoku.setField(5, 6, 2)
    sudoku.setField(5, 8, 1)
    sudoku.setField(5, 9, 8)
    sudoku.setField(6, 2, 5)
    sudoku.setField(6, 5, 3)
    sudoku.setField(6, 8, 9)
    sudoku.setField(7, 7, 2)
    sudoku.setField(8, 3, 6)
    sudoku.setField(8, 7, 8)
    sudoku.setField(8, 9, 7)
    sudoku.setField(9, 4, 3)
    sudoku.setField(9, 5, 1)
    sudoku.setField(9, 6, 6)
    sudoku.setField(9, 8, 5)
    sudoku.setField(9, 9, 9)
*/

// hard
///*
    sudoku.setField(1, 1, 6)
    sudoku.setField(1, 7, 2)
    sudoku.setField(2, 2, 5)
    sudoku.setField(2, 6, 7)
    sudoku.setField(2, 7, 9)
    sudoku.setField(3, 3, 9)
    sudoku.setField(3, 4, 1)
    sudoku.setField(3, 5, 4)
    sudoku.setField(3, 8, 3)
    sudoku.setField(4, 8, 8)
    sudoku.setField(5, 1, 4)
    sudoku.setField(5, 2, 7)
    sudoku.setField(5, 5, 5)
    sudoku.setField(5, 8, 6)
    sudoku.setField(5, 9, 9)
    sudoku.setField(6, 1, 8)
    sudoku.setField(6, 2, 3)
    sudoku.setField(7, 2, 4)
    sudoku.setField(7, 5, 3)
    sudoku.setField(7, 6, 2)
    sudoku.setField(7, 7, 7)
    sudoku.setField(8, 2, 2)
    sudoku.setField(8, 4, 6)
    sudoku.setField(8, 8, 9)
    sudoku.setField(9, 3, 8)
    sudoku.setField(9, 9, 1)
//*/

//hardest in the world
    /*
    sudoku.setField(1, 1, 8)
    sudoku.setField(2, 3, 7)
    sudoku.setField(2, 4, 5)
    sudoku.setField(2, 9, 9)
    sudoku.setField(3, 2, 3)
    sudoku.setField(3, 7, 1)
    sudoku.setField(3, 8, 8)
    sudoku.setField(4, 2, 6)
    sudoku.setField(4, 6, 1)
    sudoku.setField(4, 8, 5)
    sudoku.setField(5, 3, 9)
    sudoku.setField(5, 5, 4)
    sudoku.setField(6, 4, 7)
    sudoku.setField(6, 5, 5)
    sudoku.setField(7, 3, 2)
    sudoku.setField(7, 5, 7)
    sudoku.setField(7, 9, 4)
    sudoku.setField(8, 6, 3)
    sudoku.setField(8, 7, 6)
    sudoku.setField(8, 8, 1)
    sudoku.setField(9, 7, 8)
    */

    val sudokuCoroutines: Sudoku = sudoku.newSudoku(sudoku)

    //to set up the sudoku
    var iterator = 0
    while (iterator < 5) {
        sudoku.iterateFields()
        sudokuCoroutines.iterateFields()
        sudoku.printSudoku()
        sudokuCoroutines.printSudoku()
        iterator++
    }




    //with coroutines
    ///*
    val startTimeCoroutines = System.currentTimeMillis()

    runWithCoroutines(sudokuCoroutines)

    val endTimeCoroutines = System.currentTimeMillis()
    //*/



    //recursion
    ///*
    val startTimeRecursion = System.currentTimeMillis()

    sudoku.recursiveMeasuring(recursion = true)
    println("iteration finished\n\n${sudoku.getMapOfPossibilities()}\n${sudoku.getMapOfPossibilities().size}")

    val endTimeRecursion = System.currentTimeMillis()
    //*/


    println("Time Taken (coroutines) ${endTimeCoroutines - startTimeCoroutines}")
    println("Time Taken (recursion) ${endTimeRecursion - startTimeRecursion}")

}



fun runWithCoroutines(sudoku: Sudoku) = runBlocking {

    println("Measuring started.")

    sudoku.measureWithCoroutines()

    println("Measuring ended.")

}


