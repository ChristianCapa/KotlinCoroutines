package main

class Sudoku() {

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
        sudoku[yPos - 1][xPos - 1] = value
        defineXNinth(xPos, yPos, value)
    }


    private fun defineXNinth(xPos: Int, yPos: Int, value: Int) {
        when {
            xPos <= 3 -> {
                defineYNinth(1, yPos, value)
            }
            xPos in 3..6 -> {
                defineYNinth(2, yPos, value)
            }
            else -> {
                defineYNinth(3, yPos, value)
            }
        }
    }


    private fun defineYNinth(xNinth: Int, yPos: Int, value: Int) {
        when {
            yPos <= 3 -> {
                setNinth(xNinth, 1, value)
            }
            yPos in  3..6 -> {
                setNinth(xNinth, 2, value)
            }
            else -> {
                setNinth(xNinth, 3, value)
            }
        }
    }


    private fun setNinth(xNinth: Int, yNinth: Int, value: Int) {
        if (xNinth == 1 && yNinth == 1) {
            firstNinth.add(value)
        }
        if (xNinth == 1 && yNinth == 2) {
            secondNinth.add(value)
        }
        if (xNinth == 1 && yNinth == 3) {
            thirdNinth.add(value)
        }
        if (xNinth == 2 && yNinth == 1) {
            fourthNinth.add(value)
        }
        if (xNinth == 2 && yNinth == 2) {
            fifthNinth.add(value)
        }
        if (xNinth == 2 && yNinth == 3) {
            sixthNinth.add(value)
        }
        if (xNinth == 3 && yNinth == 1) {
            seventhNinth.add(value)
        }
        if (xNinth == 3 && yNinth == 2) {
            eighthNinth.add(value)
        }
        if (xNinth == 3 && yNinth == 3) {
            ninthNinth.add(value)
        }
    }


    fun getField(xPos: Int, yPos: Int): Int {
        return sudoku[xPos][yPos]
    }


    fun printSudoku() {
        for (array in sudoku) {
            for (value in array) {
                print("$value ")
            }
            println()
        }
    }


    fun iterateFields() {
        for (i in 1..9) {
            for (j in 1..9) {
                if (getField(i - 1, j - 1) == 0) {
                    checkRows(i - 1, j - 1)
                }
            }
        }
    }


    private fun checkRows(xPos: Int, yPos: Int) {
        val xRow: Array<Int> = Array<Int>(9) { 0 }
        val yRow: Array<Int> = Array<Int>(9) { 0 }

        for (posY in 1..9) {
            yRow[(posY - 1)] = getField(xPos, (posY - 1))
        }
        for (posX in 1..9) {
            xRow[(posX - 1)] = getField((posX - 1), yPos)
        }

        var possibleEntriesX: ArrayList<Int> = getPossibleEntries(xRow)
        var possibleEntriesY: ArrayList<Int> = getPossibleEntries(yRow)
        var possibleEntriesAtPos = possibleEntriesX.intersect(possibleEntriesY)

        println("\n\n\nSudoku at position: ${xPos + 1} | ${yPos + 1}")
        Helper.printObject("yRow", null, yRow, null)
        Helper.printObject("xRow", null, xRow, null)
        Helper.printObject("possibleEntriesY", arrayList = possibleEntriesY)
        Helper.printObject("possibleEntriesX", arrayList = possibleEntriesX)
        Helper.printObject("possibleEntriesAtPos", set = possibleEntriesAtPos)
        setPossibleEntries(possibleEntriesAtPos, xPos, yPos)

    }


    private fun getPossibleEntries(row: Array<Int>): ArrayList<Int> {
        var possibleEntries: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        var counter: Int = 1
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
        }
        else {
            sortPossibilitiesByAmount(possibleEntriesAtPos, xPos, yPos)
        }
        Helper.printObject("\nfirstNinth", arrayList = firstNinth)
        Helper.printObject("secondNinth", arrayList = secondNinth)
        Helper.printObject("thirdNinth", arrayList = thirdNinth)
        Helper.printObject("fourthNinth", arrayList = fourthNinth)
        Helper.printObject("fifthNinth", arrayList = fifthNinth)
        Helper.printObject("sixthNinth", arrayList = sixthNinth)
        Helper.printObject("seventhNinth", arrayList = seventhNinth)
        Helper.printObject("eighthNinth", arrayList = eighthNinth)
        Helper.printObject("ninthNinth", arrayList = ninthNinth)
    }


    private fun sortPossibilitiesByAmount(possibleEntriesAtPos: Set<Int>, xPos: Int, yPos: Int) {
        mapOfPossibleEntries[arrayListOf(xPos + 1, yPos + 1)] = possibleEntriesAtPos
        mapOfPossibleEntries = mapOfPossibleEntries.toList().sortedBy { (_, value) -> value.size}.toMap().toMutableMap()
    }
}



