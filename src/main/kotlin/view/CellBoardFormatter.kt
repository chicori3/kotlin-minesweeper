package view

import cell.BlankCell
import cell.Cell
import cell.CellBoard
import cell.Coordinate
import cell.MineCell
import cell.MineCount

fun format(board: CellBoard): String {
    val cells = board.cells
    val height = cells.keys.maxOf { it.y.value } + 1
    val width = cells.keys.maxOf { it.x.value } + 1
    val boardArray = initializeBoardArray(height, width)

    populateBoardArray(boardArray, cells)

    return boardArrayToString(boardArray)
}

private fun initializeBoardArray(
    height: Int,
    width: Int,
): Array<MutableList<String>> {
    return Array(height) { MutableList(width) { "C" } }
}

private fun populateBoardArray(
    boardArray: Array<MutableList<String>>,
    cells: Map<Coordinate, Cell>,
) {
    cells.forEach { (coordinate, cell) ->
        val x = coordinate.x.value
        val y = coordinate.y.value

        val emoji =
            when (cell) {
                is MineCell -> "💣"
                is BlankCell -> formatBlankCell(cell)
            }
        boardArray[y][x] = emoji
    }
}

private fun formatBlankCell(cell: BlankCell): String =
    when (cell.adjacentMineCount) {
        MineCount.ZERO -> "0️⃣"
        MineCount.ONE -> "1️⃣"
        MineCount.TWO -> "2️⃣"
        MineCount.THREE -> "3️⃣"
        MineCount.FOUR -> "4️⃣"
        MineCount.FIVE -> "5️⃣"
        MineCount.SIX -> "6️⃣"
        MineCount.SEVEN -> "7️⃣"
        MineCount.EIGHT -> "8️⃣"
    }

private fun boardArrayToString(boardArray: Array<MutableList<String>>): String {
    return boardArray.joinToString("\n") { it.joinToString("") }
}
