package main

class Sudoku() {

    private var sudoku = arrayOf<Array<Int>>()


    init {
        for (i in 1..9) {
            var array = arrayOf<Int>()
            for (j in 1..9) {
                array += 0
            }
            sudoku += array
        }
    }


    fun setField(xPos: Int, yPos: Int, value: Int) {
        sudoku[yPos - 1][xPos - 1] = value
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
                checkRows(i - 1, j - 1)
            }
        }
    }


    private fun checkRows(xPos: Int, yPos: Int) {
        println("\n\nchecking rows started")
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


        TesterClass.printArray("yRow", null, yRow)
        TesterClass.printArray("xRow", null, xRow)
        TesterClass.printArray("possibleEntriesY", possibleEntriesY, null)
        TesterClass.printArray("possibleEntriesX", possibleEntriesX, null)
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


    fun setPossibleEntries(possibleEntriesX: ArrayList<Int>, possibleEntriesY: ArrayList<Int>) {
        if (possibleEntriesX.size <= 2 && possibleEntriesY.size <= 2) {
            for (i in 1..possibleEntriesX.size) {
                for (j in 1..possibleEntriesY.size) {
                    if (possibleEntriesX[i - 1] == possibleEntriesY[j - 1]) {

                    }
                }
            }
        } else {
            sortPossibilitiesByFrequency(possibleEntriesX, possibleEntriesY)
        }
    }


    fun sortPossibilitiesByFrequency(possibleEntriesX: ArrayList<Int>, possibleEntriesY: ArrayList<Int>) {
        println("Not implemented yet!")
    }

}



