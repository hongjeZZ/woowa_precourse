package christmas.validator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DateValidatorTest {
    private val validator = DateValidator()

    @ParameterizedTest
    @ValueSource(strings = ["", "test", " "])
    fun `날짜 검증 테스트 - 숫자가 아닌 형식을 입력한 경우`(date: String) {
        assertThrows<IllegalArgumentException> { validator.validate(date) }.also { exception ->
            assertThat(exception.message).contains("[ERROR]")
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "32", "100000"])
    fun `날짜 검증 검증 테스트 - 범위를 벗어난 경우`(date: String) {
        assertThrows<IllegalArgumentException> { validator.validate(date) }.also { exception ->
            assertThat(exception.message).contains("[ERROR]")
        }
    }
}