package main


object TesterClass {

    fun printObject(name: String, arrayList: ArrayList<Int>?, array: Array<Int>?, set: Set<Int>?) {
        println("\n$name")
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
            set != null -> {
                for (value in set) {
                    print("$value ")
                }
            }
            else -> {
                println("no ArrayList<T>() or Array<T> entered")
            }
        }
    }
}
