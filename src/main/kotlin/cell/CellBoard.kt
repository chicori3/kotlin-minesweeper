package cell

class CellBoard(
    private val _cells: Map<Coordinate, Cell>,
) {
    val cellCount: Int
        get() = _cells.size

    val mineCount: Int
        get() = _cells.values.count { it is MineCell }

    val cells: Map<Coordinate, Cell>
        get() = _cells.toMap()

    companion object {
        fun of(
            height: Length,
            width: Length,
            mineCount: Count,
            mineGenerationStrategy: MineGenerationStrategy = RandomMineGenerationStrategy,
        ): CellBoard {
            val coordinates = Coordinates.of(height, width, mineGenerationStrategy)
            val cellsWithMine = coordinates.generateMineCoordinates(mineCount)

            return CellBoard(cellsWithMine)
        }
    }
}
