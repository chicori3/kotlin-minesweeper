package cell

enum class CoordinateDirection(
    val x: CoordinateValue,
    val y: CoordinateValue,
) {
    TOP_LEFT(CoordinateValue(-1), CoordinateValue(-1)),
    TOP(CoordinateValue(0), CoordinateValue(-1)),
    TOP_RIGHT(CoordinateValue(1), CoordinateValue(-1)),
    LEFT(CoordinateValue(-1), CoordinateValue(0)),
    RIGHT(CoordinateValue(1), CoordinateValue(0)),
    BOTTOM_LEFT(CoordinateValue(-1), CoordinateValue(1)),
    BOTTOM(CoordinateValue(0), CoordinateValue(1)),
    BOTTOM_RIGHT(CoordinateValue(1), CoordinateValue(1)),
}
