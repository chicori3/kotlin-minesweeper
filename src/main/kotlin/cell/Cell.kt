package cell

sealed interface Cell

data class BlankCell(val adjacentMineCount: MineCount) : Cell

data object MineCell : Cell
