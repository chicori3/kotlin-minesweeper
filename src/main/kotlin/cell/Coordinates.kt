package cell

class Coordinates(
    private val values: List<Coordinate>,
) {
    fun randomMineCoordinates(mineCount: Count): Map<Coordinate, Cell> = values.shuffled().take(mineCount.value).let(::toCells)

    private fun toCells(mineCoordinates: List<Coordinate>): Map<Coordinate, Cell> {
        val mineCells = mineCoordinates.map { it to MineCell }
        val blankCells = createBlankCells(mineCoordinates)

        return (mineCells + blankCells).toMap()
    }

    private fun createBlankCells(mineCoordinates: List<Coordinate>): List<Pair<Coordinate, BlankCell>> {
        val blankCoordinates = filterBlankCoordinates(mineCoordinates)

        return blankCoordinates.map { coordinate ->
            val adjacentMines = countAdjacentMines(mineCoordinates, coordinate)
            coordinate to BlankCell(adjacentMines)
        }
    }

    private fun filterBlankCoordinates(mineCoordinates: List<Coordinate>): List<Coordinate> {
        return values.filterNot(mineCoordinates::contains)
    }

    private fun countAdjacentMines(mineCoordinates: List<Coordinate>, coordinate: Coordinate): Int {
        val directions = CoordinateDirection.entries.toTypedArray()

        return directions.count { direction ->
            val adjacentCoordinate = Coordinate(
                coordinate.x + direction.x,
                coordinate.y + direction.y,
            )
            adjacentCoordinate in mineCoordinates
        }
    }

    companion object {
        private const val MIN_VALUE = 0

        fun of(
            height: Length,
            width: Length,
        ): Coordinates {
            return Coordinates(generateCoordinates(height, width))
        }

        private fun generateCoordinates(
            height: Length,
            width: Length,
        ): List<Coordinate> =
            (MIN_VALUE until height.value).flatMap { y ->
                generateRowCoordinates(y, width.value)
            }

        private fun generateRowCoordinates(
            y: Int,
            width: Int,
        ): List<Coordinate> =
            (MIN_VALUE until width).map { x ->
                createCoordinate(x, y)
            }

        private fun createCoordinate(
            x: Int,
            y: Int,
        ): Coordinate =
            Coordinate(
                CoordinateValue(x),
                CoordinateValue(y),
            )
    }
}
