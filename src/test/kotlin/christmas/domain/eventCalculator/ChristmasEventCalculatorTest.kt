package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ChristmasEventCalculatorTest {
    private lateinit var totalPrice: TotalPrice
    private lateinit var date: Date
    private lateinit var christmasEventCalculator: ChristmasEventCalculator

    @ParameterizedTest
    @CsvSource(
        "9_000, 24, false",
        "11_000, 3, true",
        "11_000, 26, false",
        "11_000, 25, true"
    )
    fun `이벤트 참여 자격을 판별하는 기능 테스트`(price: Int, dateNumber: Int, expected: Boolean) {
        // Given
        totalPrice = TotalPrice(price)
        date = Date(dateNumber)
        christmasEventCalculator = ChristmasEventCalculator(date, totalPrice)

        // When
        val result = christmasEventCalculator.isEligibleForEvent()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "9_000, 24, 0",
        "11_000, 3, 1_200",
        "11_000, 26, 0",
        "11_000, 25, 3_400"
    )
    fun `할인 금액을 계산하는 기능 테스트`(price: Int, dateNumber: Int, expectedDiscount: Int) {
        // Given
        totalPrice = TotalPrice(price)
        date = Date(dateNumber)
        christmasEventCalculator = ChristmasEventCalculator(date, totalPrice)

        // When
        val calculatedDiscount = christmasEventCalculator.getDiscount()

        // Then
        assertThat(calculatedDiscount).isEqualTo(expectedDiscount)
    }
}