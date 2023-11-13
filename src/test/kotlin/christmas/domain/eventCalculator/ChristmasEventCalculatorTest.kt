package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ChristmasEventCalculatorTest {

    @ParameterizedTest
    @CsvSource(
        "9000, 24, false",
        "11000, 3, true",
        "11000, 26, false",
        "11000, 25, true"
    )
    fun `이벤트 참여 자격을 판별하는 기능 테스트`(price: Int, dateNumber: Int, expected: Boolean) {
        val totalPrice = TotalPrice(price)
        val date = Date(dateNumber)
        val christmasEventCalculator = ChristmasEventCalculator(date, totalPrice)

        Assertions.assertThat(christmasEventCalculator.isEligibleForEvent()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "9000, 24, 0",
        "11000, 3, 1200",
        "11000, 26, 0",
        "11000, 25, 3400"
    )
    fun `할인 금액을 계산하는 기능 테스트`(price: Int, dateNumber: Int, expectedDiscount: Int) {
        val totalPrice = TotalPrice(price)
        val date = Date(dateNumber)
        val christmasEventCalculator = ChristmasEventCalculator(date, totalPrice)

        Assertions.assertThat(christmasEventCalculator.getDiscount()).isEqualTo(expectedDiscount)
    }
}