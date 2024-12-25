package cell

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CellBoardTest {
    @Test
    fun `입력받은 높이, 너비, 지뢰 개수에 따라 랜덤으로 지뢰가 깔린 지뢰 보드를 생성한다`() {
        val height = Length(3)
        val width = Length(3)
        val mineCount = Count(1)
        val strategy = RandomMineGenerationStrategy

        val actual =
            CellBoard.of(
                height = height,
                width = width,
                mineCount = mineCount,
                mineGenerationStrategy = strategy,
            )

        actual.cellCount shouldBe 9
        actual.mineCount shouldBe 1
    }

    @Test
    fun `빈 Cell은 주변 지뢰의 개수를 알고 있다`() {
        val height = Length(3)
        val width = Length(3)
        val mineCount = Count(1)
        val fixedCoordinates =
            listOf(
                Coordinate(
                    CoordinateValue(1),
                    CoordinateValue(1),
                ),
            )
        val strategy = FixedMineGenerationStrategy(fixedCoordinates)

        val actual =
            CellBoard.of(
                height = height,
                width = width,
                mineCount = mineCount,
                mineGenerationStrategy = strategy,
            )
        val blankCell = actual.cells[Coordinate(CoordinateValue(0), CoordinateValue(0))] as BlankCell

        actual.cellCount shouldBe 9
        actual.mineCount shouldBe 1
        blankCell.adjacentMineCount shouldBe MineCount.ONE
    }
}
