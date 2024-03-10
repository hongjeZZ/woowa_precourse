package christmas.domain.eventCalculator

import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class EventCalculatorTest {
    private class TestEventCalculator(totalPrice: TotalPrice) : EventCalculator(totalPrice) {
        override fun getDiscount(): Int = 0
    }

    @ParameterizedTest
    @CsvSource(
        "0, false",
        "9_000, false",
        "11_000, true",
        "30_000, true"
    )
    fun `이벤트 참여 자격이 되는지 판별하는 기능 테스트`(price: Int, expected: Boolean) {
        // Given
        val totalPrice = TotalPrice(price)
        val testEventCalculator = TestEventCalculator(totalPrice)

        // When
        val result = testEventCalculator.isEligibleForEvent()

        // Then
        assertThat(result).isEqualTo(expected)
    }
}