package main


object Helper {

    fun printObject(name: String, arrayList: ArrayList<Int>? = null,
                    array: Array<Int>? = null, set: Set<Int>? = null) {

        println("\n$name")
        when {
            arrayList != null -> arrayList.forEach { value ->
                    print("$value ")
                }
            array != null -> array.forEach { value ->
                print("$value ")
            }

            set != null -> set.forEach { value ->
                print("$value ")
            }
            else -> {
                println("no ArrayList<Int>(), Array<Int> or Set<Int> entered")
            }
        }
    }
}
