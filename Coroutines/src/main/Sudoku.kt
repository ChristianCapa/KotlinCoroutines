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
        sudoku[xPos][yPos] = value
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
        printArray("yRow", null, yRow)
        printArray("xRow", null, xRow)
        printArray("possibleEntriesY", possibleEntriesY, null)
        printArray("possibleEntriesX", possibleEntriesX, null)
    }




    private fun getPossibleEntries(row: Array<Int>): ArrayList<Int> {
        var possibleEntries: ArrayList<Int> = ArrayList()
        for (i in 1..9) {
            for (j in 1..9)
                if (i == row[(j - 1)]) {
                    possibleEntries.add(row[(j - 1)])
                }
        }
        return possibleEntries
    }




    private fun printArray(arrayName: String, arrayList: ArrayList<Int>?, array: Array<Int>?) {
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



