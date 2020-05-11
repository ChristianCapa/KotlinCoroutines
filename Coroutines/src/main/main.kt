package main

fun main(args: Array<String>) {
    val sudoku = Sudoku()
    sudoku.setField(1, 1, 1)
    sudoku.setField(1, 5, 6)
    sudoku.setField(1, 2, 2)
    sudoku.setField(1, 7, 5)
    sudoku.setField(1, 3, 6)
    sudoku.setField(2, 1, 7)
    sudoku.setField(2, 2, 8)
    sudoku.setField(2, 3, 1)
    sudoku.setField(2, 4, 2)
    sudoku.setField(2, 5, 3)
    sudoku.setField(2, 6, 6)
    sudoku.setField(2, 7, 4)
    sudoku.setField(3, 4, 5)
    sudoku.setField(3, 5, 7)
    sudoku.setField(3, 6, 9)
    sudoku.setField(3, 7, 8)
    sudoku.setField(3, 8, 4)
    sudoku.setField(3, 8, 3)
    sudoku.setField(3, 1, 1)
    sudoku.setField(3, 2, 2)
    sudoku.setField(4, 8, 2)
    sudoku.setField(4, 7, 6)
    sudoku.setField(4, 3, 7)
    sudoku.setField(4, 6, 5)
    sudoku.setField(5, 1, 1)
    sudoku.setField(6, 2, 2)
    sudoku.setField(6, 5, 3)
    sudoku.setField(6, 3, 6)
    sudoku.setField(6, 4, 6)
    sudoku.setField(6, 6, 4)
    sudoku.setField(6, 7, 1)
    sudoku.setField(7, 3, 3)
    sudoku.setField(8, 4, 2)
    sudoku.setField(8, 2, 7)



    sudoku.printSudoku()
    val coordinate = sudoku.getField(1, 1)
    sudoku.iterateFields()

}


/*
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
    val startTime =  System.currentTimeMillis()

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
    val startTime =  System.currentTimeMillis()

    val result = withContext(Dispatchers.Default) { calculateHardThings(10) }
    val result2 = withContext(Dispatchers.Default) { calculateHardThings(20) }
    val result3 = withContext(Dispatchers.Default) { calculateHardThings(30) }
    val sum = result + result2 + result3
    println("async/await result = $sum")

    val endTime = System.currentTimeMillis()
    println("Time Taken ${endTime - startTime}")

}

*/
