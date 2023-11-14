package christmas.validator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MenuCountValidatorTest {
    private val validator = MenuCountValidator()

    @Test
    fun `메뉴의 개수가 범위를 벗어난 경우 - 0을 입력한 경우`() {
        // Given
        val invalidMenuCounts = listOf(0, 5, 10)

        // When, Then
        assertThrows<IllegalArgumentException> {
            validator.validate(invalidMenuCounts)
        }.also { exception ->
            assertThat(exception.message).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `메뉴의 개수가 범위를 벗어난 경우 - 음수를 입력한 경우`() {
        // Given
        val invalidMenuCounts = listOf(-5, 10)

        // When, Then
        assertThrows<IllegalArgumentException> {
            validator.validate(invalidMenuCounts)
        }.also { exception ->
            assertThat(exception.message).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `메뉴 개수의 총합이 20개를 넘기는 경우`() {
        // Given
        val invalidMenuCounts = listOf(6, 5, 10)

        // When, Then
        assertThrows<IllegalArgumentException> {
            validator.validate(invalidMenuCounts)
        }.also { exception ->
            assertThat(exception.message).contains("[ERROR] 메뉴는 최대 20개까지만 주문할 수 있습니다.")
        }
    }
}