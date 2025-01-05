package cell

sealed interface GameResult {
    data object LOSE : GameResult

    data class CONTINUE(val revealedCells: Set<Coordinate>) : GameResult
}
