package main

class Sudoku {

    private var sudoku = arrayOf<Array<Int>>()
    private var mapOfPossibleEntries: MutableMap<ArrayList<Int>, Set<Int>> = HashMap<ArrayList<Int>, Set<Int>>()
    private var firstNinth: ArrayList<Int> = arrayListOf()
    private var secondNinth: ArrayList<Int> = arrayListOf()
    private var thirdNinth: ArrayList<Int> = arrayListOf()
    private var fourthNinth: ArrayList<Int> = arrayListOf()
    private var fifthNinth: ArrayList<Int> = arrayListOf()
    private var sixthNinth: ArrayList<Int> = arrayListOf()
    private var seventhNinth: ArrayList<Int> = arrayListOf()
    private var eighthNinth: ArrayList<Int> = arrayListOf()
    private var ninthNinth: ArrayList<Int> = arrayListOf()


    init {
        for (i in 1..9) {
            var array = arrayOf<Int>()
            for (j in 1..9) {
                array += 0
            }
            sudoku += array
        }
    }


    fun getMapOfPossibilities(): MutableMap<ArrayList<Int>, Set<Int>> {
        return mapOfPossibleEntries
    }


    fun setField(xPos: Int, yPos: Int, value: Int) {
        sudoku[xPos - 1][yPos - 1] = value
        getNinth(xPos, yPos)?.add(value)
    }


    private fun getNinth(xPos: Int, yPos: Int): ArrayList<Int>? {
        when {
            xPos <= 3 && yPos <= 3 -> {
                return firstNinth
            }
            xPos in 4..6 && yPos <= 3 -> {
                return secondNinth
            }
            xPos in 7..9 && yPos <= 3 -> {
                return thirdNinth
            }
            xPos <= 3 && yPos in 4..6 -> {
                return fourthNinth
            }
            xPos in 4..6 && yPos in 4..6 -> {
                return fifthNinth
            }
            xPos in 7..9 && yPos in 4..6 -> {
                return sixthNinth
            }
            xPos <= 3 && yPos in 7..9 -> {
                return seventhNinth
            }
            xPos in 4..6 && yPos in 7..9 -> {
                return eighthNinth
            }
            xPos in 7..9 && yPos in 7..9 -> {
                return ninthNinth
            }
            else -> return null
        }
    }


    fun getField(xPos: Int, yPos: Int): Int {
        return sudoku[xPos][yPos]
    }


    fun printSudoku() {
        for (y in 1..9) {
            for (x in 1..9) {
                print("${getField(x - 1, y - 1)} ")
                if (x == 9) {
                    println()
                }
            }
        }
    }


    fun iterateFields() {
        for (y in 1..9) {
            for (x in 1..9) {
                if (getField(x - 1, y - 1) == 0) {
                    checkRows(x - 1, y - 1)
                }
            }
        }
    }


    private fun checkRows(xPos: Int, yPos: Int) {
        val xRow: Array<Int> = Array<Int>(9) { 0 }
        val yRow: Array<Int> = Array<Int>(9) { 0 }

        for (posX in 1..9) {
            xRow[(posX - 1)] = getField((posX - 1), yPos)
        }
        for (posY in 1..9) {
            yRow[(posY - 1)] = getField(xPos, (posY - 1))
        }

        val possibleEntriesX: ArrayList<Int> = getPossibleEntries(xRow)
        val possibleEntriesY: ArrayList<Int> = getPossibleEntries(yRow)
        var possibleEntriesAtPos = possibleEntriesX.intersect(possibleEntriesY)
        val ninth = getNinth(xPos, yPos)
        //possibleEntriesAtPos = ninth?.let { possibleEntriesAtPos.toIntArray().intersect(it) }!!


        println("\n\n\nSudoku at position: ${xPos + 1} | ${yPos + 1}")
        Helper.printObject("xRow", null, xRow, null)
        Helper.printObject("yRow", null, yRow, null)
        Helper.printObject("possibleEntriesX", arrayList = possibleEntriesX)
        Helper.printObject("possibleEntriesY", arrayList = possibleEntriesY)
        Helper.printObject("possibleEntriesAtPos", set = possibleEntriesAtPos)
        setPossibleEntries(possibleEntriesAtPos, xPos, yPos)

    }


    private fun getPossibleEntries(row: Array<Int>): ArrayList<Int> {
        val possibleEntries: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        var counter = 1
        for (i in 1..9) {
            for (j in 1..9) {
                if (i == row[(j - 1)]) {
                    while (counter <= possibleEntries.size) {
                        if (i == possibleEntries[counter - 1]) {
                            possibleEntries.remove(i)
                        }
                        counter++
                    }
                    counter = 1
                }
            }
        }
        return possibleEntries
    }


    private fun setPossibleEntries(possibleEntriesAtPos: Set<Int>, xPos: Int, yPos: Int) {
        if (possibleEntriesAtPos.size == 1) {
            setField(xPos + 1, yPos + 1, possibleEntriesAtPos.elementAt(0))
        } else {
            sortPossibilitiesByAmount(possibleEntriesAtPos, xPos, yPos)
        }
    }

    private fun sortPossibilitiesByAmount(possibleEntriesAtPos: Set<Int>, xPos: Int, yPos: Int) {
        mapOfPossibleEntries[arrayListOf(xPos + 1, yPos + 1)] = possibleEntriesAtPos
        mapOfPossibleEntries = mapOfPossibleEntries.toList().sortedBy { (_, value) -> value.size }.toMap().toMutableMap()
    }
}



