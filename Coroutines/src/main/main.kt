package main

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main(args: Array<String>) {

    val sudoku = Sudoku()
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

    var iterator = 0
    while (sudoku.emptyCounter() != 0) {
        sudoku.iterateFields()
        sudoku.printSudoku()
        iterator++
        if (iterator > 50) {
            break
        }
    }

    println("The algorithm needed $iterator iterations.")


    val dispatcher = Dispatcher(sudoku.getMapOfPossibilities())
    //exampleLaunchCoroutinesScope()
}


suspend fun printlnDelayed(message: String) {
    delay(1000)
    println(message)
}

suspend fun calculateHardThings(startNum: Int): Int {
    delay(1000)
    return startNum * 10
}

fun exampleBlocking() = runBlocking {
    println("one")
    printlnDelayed("two")
    println("three")
}

fun exampleBlockingDispatcher() {
    runBlocking(Dispatchers.Default) {
        println("one - from thread ${Thread.currentThread().name}")
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
}

fun exampleLaunchGlobal() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")

    GlobalScope.launch {
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
    delay(3000)
}

fun exampleLaunchGlobalWaiting() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")

    val job = GlobalScope.launch {
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
    job.join()
}

fun exampleLaunchCoroutinesScope() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")

    val customDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

    launch(customDispatcher) {
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
    (customDispatcher.executor as ExecutorService).shutdown()
}

fun exampleAsyncAwait(threadCount: Int) = runBlocking {
    val startTime = System.currentTimeMillis()

    for (i: Int in threadCount until threadCount) {
        println("hi")
    }

    val deferred1 = async { calculateHardThings(10) }
    val deferred2 = async { calculateHardThings(20) }
    val deferred3 = async { calculateHardThings(30) }

    val sum = deferred1.await() + deferred2.await() + deferred3.await()
    println("async/await result = $sum")

    val endTime = System.currentTimeMillis()
    println("Time Taken ${endTime - startTime}")

}

fun exampleWithContext() = runBlocking {
    val startTime = System.currentTimeMillis()

    val result = withContext(Dispatchers.Default) { calculateHardThings(10) }
    val result2 = withContext(Dispatchers.Default) { calculateHardThings(20) }
    val result3 = withContext(Dispatchers.Default) { calculateHardThings(30) }
    val sum = result + result2 + result3
    println("async/await result = $sum")

    val endTime = System.currentTimeMillis()
    println("Time Taken ${endTime - startTime}")

}


