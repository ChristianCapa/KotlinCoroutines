package main

import kotlinx.coroutines.*


fun main(args: Array<String>) = runBlocking {
    val fImpl = FImpl()
    val resultList = withContext(Dispatchers.Default) { execute(fImpl, 10000000) }
    resultList.forEach { println(it) }
}

suspend fun execute(f: F, n: Int): IntArray {
    val myScope = CoroutineScope(Dispatchers.Default)
    val deferredList = arrayListOf<Deferred<Int>>()
    val result = Result(n)

    for (i in 0 until n) {
        val deferred = myScope.async { f.f(i) }
        deferredList.add(deferred)
    }

    deferredList.forEach { result.setResult(it.await()) }
    return result.getResultList()
}

