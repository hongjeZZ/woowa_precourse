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
    @CsvSource("9000, false", "11000,true")
    fun `이벤트 참여 자격이 되는지 판별하는 기능 테스트`(price: Int, expected: Boolean) {
        val totalPrice = TotalPrice(price)
        val testEventCalculator = TestEventCalculator(totalPrice)

        assertThat(testEventCalculator.isEligibleForEvent()).isEqualTo(expected)
    }
}