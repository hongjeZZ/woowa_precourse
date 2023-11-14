package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SpecialEventCalculatorTest {
    private lateinit var totalPrice: TotalPrice
    private lateinit var date: Date
    private lateinit var specialEventCalculator: SpecialEventCalculator

    @ParameterizedTest
    @CsvSource(
        "1 : false",
        "2 : false",
        "3 : true",
        "10 : true",
        "17 : true",
        "24 : true",
        "25 : true",
        "31 : true",
        delimiter = ':'
    )
    fun `이벤트 참여 자격을 판별하는 기능 테스트`(dateNumber: Int, expected: Boolean) {
        // Given
        totalPrice = TotalPrice(10_000)
        date = Date(dateNumber)
        specialEventCalculator = SpecialEventCalculator(date, totalPrice)

        // When
        val result = specialEventCalculator.isEligibleForEvent()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "1 : 0",
        "2 : 0",
        "3 : 1_000",
        "10 : 1_000",
        "17 : 1_000",
        "24 : 1_000",
        "25 : 1_000",
        "31 : 1_000",
        delimiter = ':'
    )
    fun `할인 금액을 계산하는 기능 테스트`(dateNumber: Int, expectedDiscount: Int) {
        // Given
        totalPrice = TotalPrice(10_000)
        date = Date(dateNumber)
        specialEventCalculator = SpecialEventCalculator(date, totalPrice)

        // When
        val calculatedDiscount = specialEventCalculator.getDiscount()

        // Then
        assertThat(calculatedDiscount).isEqualTo(expectedDiscount)
    }
}