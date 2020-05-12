package main


object TesterClass {

    fun printArray(arrayName: String, arrayList: ArrayList<Int>?, array: Array<Int>?) {
        println("\n${arrayName}")
        when {
            arrayList != null -> {
                for (value in arrayList) {
                    print("$value ")
                }
            }
            array != null -> {
                for (value in array) {
                    print("$value ")
                }
            }
            else -> {
                println("no ArrayList<T>() or Array<T> entered")
            }
        }
    }

}
