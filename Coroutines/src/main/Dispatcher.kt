package main

class Dispatcher(map: MutableMap<ArrayList<Int>, Set<Int>>) {

    private var mapOfPossibleEntries: MutableMap<ArrayList<Int>, Set<Int>> = HashMap<ArrayList<Int>, Set<Int>>()

    init {
        mapOfPossibleEntries = map
    }

    fun printMap() {
        println(mapOfPossibleEntries.toString())
    }

}