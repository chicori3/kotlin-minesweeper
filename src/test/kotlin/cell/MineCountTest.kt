package cell

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MineCountTest {
    @ValueSource(ints = [-1, 9])
    @ParameterizedTest
    fun `지뢰 개수 생성 시 0 ~ 8 범위의 값을 벗어나면 예외가 발생한다`(value: Int) {
        shouldThrow<IllegalArgumentException> {
            MineCount.of(value)
        }
    }
}
