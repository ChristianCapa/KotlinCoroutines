package main

class Result(private val n: Int) {

    private var resultList = IntArray(n)
    private var resultCounter = 0

    @Synchronized
    fun setResult(result: Int) {
        resultList[resultCounter] = result
        resultCounter++
    }

    @Synchronized
    fun getResultList(): IntArray {
        return resultList
    }

}