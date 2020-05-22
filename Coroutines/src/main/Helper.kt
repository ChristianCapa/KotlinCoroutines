package main


object Helper {

    fun printObject(name: String, arrayList: ArrayList<Int>? = null, array: Array<Int>? = null, arrayList2D: ArrayList<ArrayList<Int>>? = null, set: Set<Int>? = null) {
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

            arrayList2D != null -> {
                for (array in arrayList2D) {
                    for (value in array) {
                        print("$value ")
                    }
                    println()
                }
            }

            set != null -> {
                for (value in set) {
                    print("$value ")
                }
            }
            else -> {
                println("no ArrayList<Int>(), Array<Int> or Set<Int> entered")
            }
        }
    }
}
