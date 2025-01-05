package cell

class CellBoard(
    private val _cells: MutableMap<Coordinate, Cell>,
) {
    val cellCount: Int
        get() = _cells.size

    val mineCount: Int
        get() = _cells.values.count { it is MineCell }

    val cells: Map<Coordinate, Cell>
        get() = _cells.toMap()

    fun handleInput(coordinate: Coordinate): GameResult {
        val cell = _cells[coordinate]
        check(cell != null) { "좌표가 존재하지 않습니다." }
        check(!cell.revealed) { "이미 공개된 좌표입니다." }

        return if (cell is MineCell) {
            GameResult.LOSE
        } else {
            revealCells(coordinate, cell)
        }
    }

    private fun revealCells(
        startCoordinate: Coordinate,
        startCell: Cell,
    ): GameResult {
        val revealed = mutableSetOf<Coordinate>()
        floodFill(startCoordinate, startCell, revealed)
        return GameResult.CONTINUE(revealed)
    }

    private fun floodFill(
        coordinate: Coordinate,
        cell: Cell,
        revealed: MutableSet<Coordinate>,
    ) {
        if (cell is MineCell || cell.revealed) return

        _cells[coordinate] = cell.reveal()
        revealed.add(coordinate)

        if (cell.shouldFloodFill()) {
            floodFillAdjacentCells(coordinate, revealed)
        }
    }

    private fun floodFillAdjacentCells(
        coordinate: Coordinate,
        revealed: MutableSet<Coordinate>,
    ) {
        val directions = CoordinateDirection.entries.toTypedArray()
        directions.forEach { direction ->
            val adjacentCoordinate =
                Coordinate(
                    coordinate.x + direction.x,
                    coordinate.y + direction.y,
                )
            _cells[adjacentCoordinate]?.let { adjacentCell ->
                floodFill(adjacentCoordinate, adjacentCell, revealed)
            }
        }
    }

    companion object {
        fun of(
            height: Length,
            width: Length,
            mineCount: Count,
            mineGenerationStrategy: MineGenerationStrategy = RandomMineGenerationStrategy,
        ): CellBoard {
            val coordinates = Coordinates.of(height, width, mineGenerationStrategy)
            val cellsWithMine = coordinates.generateMineCoordinates(mineCount)

            return CellBoard(cellsWithMine.toMutableMap())
        }
    }
}
