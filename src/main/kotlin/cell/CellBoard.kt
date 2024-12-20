package cell

class CellBoard(
    private val cells: Map<Coordinate, Cell>,
) {
    val cellCount: Int
        get() = cells.size

    val mineCount: Int
        get() = cells.values.count { it is MineCell }

    companion object {
        private const val MIN_VALUE = 0

        fun of(
            height: Int,
            width: Int,
            mineCount: Int,
        ): CellBoard {
            require(height > MIN_VALUE) { "높이는 0 보다 커야 합니다." }
            require(width > MIN_VALUE) { "너비는 0 보다 커야 합니다." }

            val coordinates = Coordinates.of(height, width)
            val cellsWithMine = coordinates.randomMineCoordinates(mineCount)

            return CellBoard(cellsWithMine)
        }
    }
}
