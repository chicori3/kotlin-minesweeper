package cell

sealed interface MineGenerationStrategy {
    fun generateMineCoordinates(values: List<Coordinate>, mineCount: Count): List<Coordinate>
}

data object RandomMineGenerationStrategy : MineGenerationStrategy {
    override fun generateMineCoordinates(values: List<Coordinate>, mineCount: Count): List<Coordinate> {
        return values.shuffled().take(mineCount.value)
    }
}

class FixedMineGenerationStrategy(private val fixedCoordinates: List<Coordinate>) : MineGenerationStrategy {
    override fun generateMineCoordinates(values: List<Coordinate>, mineCount: Count): List<Coordinate> {
        require(fixedCoordinates.size == mineCount.value) { "지정한 좌표의 개수와 지뢰의 개수가 일치해야 합니다." }
        return fixedCoordinates
    }
}
