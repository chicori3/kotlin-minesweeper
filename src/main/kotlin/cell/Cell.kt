package cell

sealed interface Cell

data class BlankCell(val adjacentMineCount: Int) : Cell

data object MineCell : Cell
