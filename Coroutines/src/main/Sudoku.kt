package main


class Sudoku {

    private var sudoku = arrayOf<Array<Int>>()
    private var mapOfPossibleEntries: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var firstNinth: ArrayList<Int> = arrayListOf()
    private var secondNinth: ArrayList<Int> = arrayListOf()
    private var thirdNinth: ArrayList<Int> = arrayListOf()
    private var fourthNinth: ArrayList<Int> = arrayListOf()
    private var fifthNinth: ArrayList<Int> = arrayListOf()
    private var sixthNinth: ArrayList<Int> = arrayListOf()
    private var seventhNinth: ArrayList<Int> = arrayListOf()
    private var eighthNinth: ArrayList<Int> = arrayListOf()
    private var ninthNinth: ArrayList<Int> = arrayListOf()
    private var parentContainer: MutableSet<MutableMap<ArrayList<Int>, Set<Int>>> = mutableSetOf()
    private var firstNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var secondNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var thirdNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var fourthNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var fifthNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var sixthNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var seventhNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var eighthNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()
    private var ninthNinthContainer: MutableMap<ArrayList<Int>, Set<Int>> = mutableMapOf()

    init {
        sudoku = initSudoku()
    }

    /*
    fun getSudoku(): Array<Array<Int>> {
        return sudoku
    }
    */

    fun getMapOfPossibilities(): MutableMap<ArrayList<Int>, Set<Int>> {
        return mapOfPossibleEntries
    }

    /*
    fun setMapOfPossibilities(mapOfPossibleEntries: MutableMap<ArrayList<Int>, Set<Int>>) {
        for (entry in mapOfPossibleEntries) {
            this.mapOfPossibleEntries.apply { entry }
        }
    }
    */

    private fun getField(xPos: Int, yPos: Int): Int {
        return sudoku[xPos][yPos]
    }


    fun setField(xPos: Int, yPos: Int, value: Int): Boolean {
        val ninth = getNinth(xPos, yPos)?.first
        if (ninth != null) {
            if (!(ninth.contains(value)) && !(calculateXRow(yPos - 1).contains(value)) && !(calculateYRow(xPos - 1).contains(value))) {
                sudoku[xPos - 1][yPos - 1] = value
                ninth.add(value)
                return true
            }
        }
        return false
    }

    private fun setCopiedFields(xPos: Int, yPos: Int, value: Int) {
        sudoku[xPos][yPos] = value
    }


    private fun getNinth(xPos: Int, yPos: Int): Pair<ArrayList<Int>, MutableMap<ArrayList<Int>, Set<Int>>>? {
        when {
            xPos <= 3 && yPos <= 3 -> {
                return Pair(firstNinth, firstNinthContainer)
            }
            xPos in 4..6 && yPos <= 3 -> {
                return Pair(secondNinth, secondNinthContainer)
            }
            xPos in 7..9 && yPos <= 3 -> {
                return Pair(thirdNinth, thirdNinthContainer)
            }
            xPos <= 3 && yPos in 4..6 -> {
                return Pair(fourthNinth, fourthNinthContainer)
            }
            xPos in 4..6 && yPos in 4..6 -> {
                return Pair(fifthNinth, fifthNinthContainer)
            }
            xPos in 7..9 && yPos in 4..6 -> {
                return Pair(sixthNinth, sixthNinthContainer)
            }
            xPos <= 3 && yPos in 7..9 -> {
                return Pair(seventhNinth, seventhNinthContainer)
            }
            xPos in 4..6 && yPos in 7..9 -> {
                return Pair(eighthNinth, eighthNinthContainer)
            }
            xPos in 7..9 && yPos in 7..9 -> {
                return Pair(ninthNinth, ninthNinthContainer)
            }
            else -> return null
        }
    }


    fun printSudoku() {
        println()
        for (y in 1..9) {
            for (x in 1..9) {
                print("${getField(x - 1, y - 1)} ")
                if (x == 9) {
                    println()
                }
            }
        }
    }

    private fun copyFields(): MutableMap<ArrayList<Int>, Int> {
        var fields: MutableMap<ArrayList<Int>, Int> = mutableMapOf()
        for (y in 1..9) {
            for (x in 1..9) {
                fields[arrayListOf(x - 1, y - 1)] = getField(x - 1, y - 1)
            }
        }
        return fields
    }

    fun iterateFields() {
        for (y in 1..9) {
            for (x in 1..9) {
                if (getField(x - 1, y - 1) == 0) {
                    checkRows(x - 1, y - 1)
                }
            }
        }
        attachToParent()
        invertedMeasuring()
    }


    private fun checkRows(xPos: Int, yPos: Int) {
        val xRow: Array<Int> = calculateXRow(yPos)
        val yRow: Array<Int> = calculateYRow(xPos)
        val possibleEntriesX: ArrayList<Int> = getPossibleEntries(xRow)
        val possibleEntriesY: ArrayList<Int> = getPossibleEntries(yRow)
        var possibleEntriesAtPos = possibleEntriesX.intersect(possibleEntriesY)

        possibleEntriesAtPos = intersectWithNinth(xPos, yPos, possibleEntriesAtPos)
        getNinth(xPos + 1, yPos + 1)?.second?.put(arrayListOf(xPos, yPos), possibleEntriesAtPos)
        setPossibleEntries(possibleEntriesAtPos, xPos, yPos)


        /*
        println("\n\n\nSudoku at position: ${xPos + 1} | ${yPos + 1}")
        Helper.printObject("xRow", array = xRow)
        Helper.printObject("yRow", array = yRow)
        Helper.printObject("possibleEntriesX", arrayList = possibleEntriesX)
        Helper.printObject("possibleEntriesY", arrayList = possibleEntriesY)
        Helper.printObject("possibleEntriesAtPos", set = possibleEntriesAtPos)
        */
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


    private fun intersectWithNinth(xPos: Int, yPos: Int, possibleEntriesAtPos: Set<Int>): Set<Int> {
        val ninth = getNinth(xPos + 1, yPos + 1)?.first
        var setOfEntries: Set<Int> = mutableSetOf()
        if (ninth != null) {
            setOfEntries = possibleEntriesAtPos.subtract(ninth)
        }
        return setOfEntries
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


    fun emptyCounter(): Int {
        var emptyCounter = 0

        for (y in 1..9) {
            for (x in 1..9) {
                if (getField(x - 1, y - 1) == 0) {
                    emptyCounter++
                }
            }
        }
        println("\nEmpty Counter: $emptyCounter \n")
        return emptyCounter
    }


    private fun calculateXRow(yPos: Int): Array<Int> {
        val xRow = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (posX in 1..9) {
            xRow[(posX - 1)] = getField((posX - 1), yPos)
        }
        return xRow
    }


    private fun calculateYRow(xPos: Int): Array<Int> {
        val yRow = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (posY in 1..9) {
            yRow[(posY - 1)] = getField(xPos, (posY - 1))
        }
        return yRow
    }


    private fun initSudoku(): Array<Array<Int>> {
        var sudoku: Array<Array<Int>> = arrayOf()
        for (i in 1..9) {
            var array = arrayOf<Int>()
            for (j in 1..9) {
                array += 0
            }
            sudoku += array
        }
        return sudoku
    }


/*
    private fun sudokuCopy(sudoku: Array<Array<Int>>): Array<Array<Int>> {
        var copy: Array<Array<Int>> = arrayOf<Array<Int>>()
        copy = initSudoku()

        for (y in 1..9) {
            for (x in 1..9) {
                copy[x - 1][y - 1] = sudoku[x - 1][y - 1]
            }
        }
        return copy
    }


    private fun deleteEntries(positions: ArrayList<ArrayList<Int>>) {
        for (position in positions) {
            println(mapOfPossibleEntries.remove(position))
        }
    }
*/


    private fun invertedMeasuring() {
        val numberObject = NumberObject()
        for (container in parentContainer) {
            for (valueSet in container.values) {
                for (value in valueSet) {
                    if (value == 1) {
                        numberObject.one++
                    }
                    if (value == 2) {
                        numberObject.two++
                    }
                    if (value == 3) {
                        numberObject.three++
                    }
                    if (value == 4) {
                        numberObject.four++
                    }
                    if (value == 5) {
                        numberObject.five++
                    }
                    if (value == 6) {
                        numberObject.six++
                    }
                    if (value == 7) {
                        numberObject.seven++
                    }
                    if (value == 8) {
                        numberObject.eight++
                    }
                    if (value == 9) {
                        numberObject.nine++
                    }
                }
            }
            val numberList: ArrayList<Int> = numberObject.getEachNumberMatching(1)
            for (number in numberList) {
                setInverts(container, number)
            }
            numberObject.resetNumbers()
        }
    }


    private fun setInverts(container: MutableMap<ArrayList<Int>, Set<Int>>, number: Int) {
        for (key in container.keys) {
            val values = container[key]
            if (values != null) {
                for (value in values) {
                    if (value == number) {
                        setField(key.elementAt(0) + 1, key.elementAt(1) + 1, number)
                    }
                }
            }
        }
    }


    private fun attachToParent() {
        parentContainer.add(firstNinthContainer)
        parentContainer.add(secondNinthContainer)
        parentContainer.add(thirdNinthContainer)
        parentContainer.add(fourthNinthContainer)
        parentContainer.add(fifthNinthContainer)
        parentContainer.add(sixthNinthContainer)
        parentContainer.add(seventhNinthContainer)
        parentContainer.add(eighthNinthContainer)
        parentContainer.add(ninthNinthContainer)
    }


    fun recursiveMeasuring() {
        val startingPoint = mapOfPossibleEntries.keys.elementAt(0)
        val sudokus = mutableListOf<Sudoku>()

        for (i in 0 until (mapOfPossibleEntries.values.elementAt(0).size)) {
            val sudokuCopy = newSudoku()
            println(startingPoint)
            println(startingPoint.component1())
            println(startingPoint.component2())
            println(mapOfPossibleEntries[startingPoint]!!.elementAt(i))
            sudokuCopy.setField(startingPoint.component1(), startingPoint.component2(), mapOfPossibleEntries[startingPoint]!!.elementAt(i))
            sudokuCopy.mapOfPossibleEntries.remove(startingPoint)
            sudokus.add(i, sudokuCopy)
        }

        println(sudokus)
        for (sudoku in sudokus) {
            sudoku.printSudoku()
            println(sudoku.mapOfPossibleEntries)
        }
    }


    private fun copyNinth(ninth: ArrayList<Int>): ArrayList<Int> {
        val ninthCopy = arrayListOf<Int>()
        for (value in ninth) {
            ninthCopy.add(value)
        }
        return ninthCopy
    }

    private fun copyMap(mapOfPossibleEntries: MutableMap<ArrayList<Int>, Set<Int>>): MutableMap<ArrayList<Int>, Set<Int>> {
        val mapCopy = mutableMapOf<ArrayList<Int>, Set<Int>>()
        for (entry in mapOfPossibleEntries) {
            mapCopy[entry.component1()] = entry.component2()
        }
        return mapCopy
    }

    private fun newSudoku(): Sudoku {
        val sudokuCopy = Sudoku()
        sudokuCopy.mapOfPossibleEntries = copyMap(mapOfPossibleEntries)
        sudokuCopy.firstNinth = copyNinth(getNinth(0, 0)!!.first)
        sudokuCopy.secondNinth = copyNinth(getNinth(4, 0)!!.first)
        sudokuCopy.thirdNinth = copyNinth(getNinth(7, 0)!!.first)
        sudokuCopy.fourthNinth = copyNinth(getNinth(0, 4)!!.first)
        sudokuCopy.fifthNinth = copyNinth(getNinth(4, 4)!!.first)
        sudokuCopy.seventhNinth = copyNinth(getNinth(7, 4)!!.first)
        sudokuCopy.sixthNinth = copyNinth(getNinth(0, 7)!!.first)
        sudokuCopy.eighthNinth = copyNinth(getNinth(4, 7)!!.first)
        sudokuCopy.ninthNinth = copyNinth(getNinth(7, 7)!!.first)
        val fields = copyFields()
        for (entry in fields) {
            sudokuCopy.setCopiedFields(entry.component1().elementAt(0), entry.component1().elementAt(1), entry.component2())
        }
        return sudokuCopy
    }
}


