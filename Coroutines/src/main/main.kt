package main

import kotlinx.coroutines.*

fun main(args: Array<String>) {

    val sudoku = Sudoku()

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

    //first round
    val sudokuCoroutines: Sudoku = sudoku.newSudoku(sudoku)

    //second round
    val sudokuCoroutines1: Sudoku = sudoku.newSudoku(sudoku)
    val sudokuCoroutines2: Sudoku = sudoku.newSudoku(sudoku)
    val sudokuRecursion1: Sudoku = sudoku.newSudoku(sudoku)
    val sudokuRecursion2: Sudoku = sudoku.newSudoku(sudoku)


    //third round
    val sudokuCoroutines3: Sudoku = sudoku.newSudoku(sudoku)
    val sudokuCoroutines4: Sudoku = sudoku.newSudoku(sudoku)
    val sudokuCoroutines5: Sudoku = sudoku.newSudoku(sudoku)
    val sudokuRecursion3: Sudoku = sudoku.newSudoku(sudoku)
    val sudokuRecursion4: Sudoku = sudoku.newSudoku(sudoku)
    val sudokuRecursion5: Sudoku = sudoku.newSudoku(sudoku)


    //to set up the sudokus
    var iterator = 0
    while (iterator < 5) {
        sudoku.iterateFields()
        sudokuCoroutines.iterateFields()
        sudokuCoroutines1.iterateFields()
        sudokuCoroutines2.iterateFields()
        sudokuRecursion1.iterateFields()
        sudokuRecursion2.iterateFields()
        sudokuCoroutines3.iterateFields()
        sudokuCoroutines4.iterateFields()
        sudokuCoroutines5.iterateFields()
        sudokuRecursion3.iterateFields()
        sudokuRecursion4.iterateFields()
        sudokuRecursion5.iterateFields()
        iterator++
    }


    if (sudoku.emptyCounter(withPrintLn = true) != 0) {

        //with coroutines
        val startTimeCoroutines = System.currentTimeMillis()
        runWithCoroutines(sudokuCoroutines)
        val endTimeCoroutines = System.currentTimeMillis()


        //recursion
        val startTimeRecursion = System.currentTimeMillis()
        sudoku.recursiveMeasuring(recursion = true)
        val endTimeRecursion = System.currentTimeMillis()


        //two sudokus coroutines
        val startTimeCoroutines2 = System.currentTimeMillis()
        runMultipleWithCoroutines(arrayListOf(sudokuCoroutines1, sudokuCoroutines2))
        val endTimeCoroutines2 = System.currentTimeMillis()


        //two sudokus recursion
        val startTimeRecursion2 = System.currentTimeMillis()
        sudokuRecursion1.recursiveMeasuring(recursion = true)
        sudokuRecursion2.recursiveMeasuring(recursion = true)
        val endTimeRecursion2 = System.currentTimeMillis()


        //three sudokus coroutines
        val startTimeCoroutines3 = System.currentTimeMillis()
        runMultipleWithCoroutines(arrayListOf(sudokuCoroutines3, sudokuCoroutines4, sudokuCoroutines5))
        val endTimeCoroutines3 = System.currentTimeMillis()


        //three sudokus recursion
        val startTimeRecursion3 = System.currentTimeMillis()
        sudokuRecursion3.recursiveMeasuring(recursion = true)
        sudokuRecursion4.recursiveMeasuring(recursion = true)
        sudokuRecursion5.recursiveMeasuring(recursion = true)
        val endTimeRecursion3 = System.currentTimeMillis()


        //timer
        println("Slow measuring at the first part can occur due to a cpu delay\n")
        println("Time Taken (1 sudoku, coroutines): ${(endTimeCoroutines - startTimeCoroutines) / 1000.0} seconds")
        println("Time Taken (1 sudoku, recursion): ${(endTimeRecursion - startTimeRecursion) / 1000.0} seconds\n")
        println("Time Taken (2 sudokus, coroutines): ${(endTimeCoroutines2 - startTimeCoroutines2) / 1000.0} seconds")
        println("Time Taken (2 sudokus, recursion): ${(endTimeRecursion2 - startTimeRecursion2) / 1000.0} seconds\n")
        println("Time Taken (3 sudokus, coroutines): ${(endTimeCoroutines3 - startTimeCoroutines3) / 1000.0} seconds")
        println("Time Taken (3 sudokus, recursion): ${(endTimeRecursion3 - startTimeRecursion3) / 1000.0} seconds")
    }
}


fun runWithCoroutines(sudoku: Sudoku) = runBlocking {
    sudoku.measureWithCoroutines()
}


fun runMultipleWithCoroutines(sudokuList: ArrayList<Sudoku>) = runBlocking {
    val jobList = arrayListOf<Job>()
    sudokuList.forEach {
        val job = launch { it.measureWithCoroutines() }
        jobList.add(job)
    }
    jobList.joinAll()
}