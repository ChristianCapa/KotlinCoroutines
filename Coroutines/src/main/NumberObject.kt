package main

class NumberObject {

    var one = 0
    var two = 0
    var three = 0
    var four = 0
    var five = 0
    var six = 0
    var seven = 0
    var eight = 0
    var nine = 0

    fun resetNumbers() {
        one = 0
        two = 0
        three = 0
        four = 0
        five = 0
        six = 0
        seven = 0
        eight = 0
        nine = 0
    }

    fun getEachNumberMatching(condition: Int): ArrayList<Int> {
        val numberList: ArrayList<Int> = arrayListOf(one, two, three, four, five, six, seven, eight, nine)
        val returnList: ArrayList<Int> = arrayListOf()
        for (index in 0 until numberList.size) {
            if (numberList[index] == condition) {
                returnList.add(index + 1)
            }
        }
        return returnList
    }

}