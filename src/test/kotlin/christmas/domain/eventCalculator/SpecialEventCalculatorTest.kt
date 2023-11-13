package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SpecialEventCalculatorTest {
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
        val totalPrice = TotalPrice(10_000)
        val date = Date(dateNumber)
        val specialEventCalculator = SpecialEventCalculator(date, totalPrice)

        Assertions.assertThat(specialEventCalculator.isEligibleForEvent()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "1 : 0",
        "2 : 0",
        "3 : 1000",
        "10 : 1000",
        "17 : 1000",
        "24 : 1000",
        "25 : 1000",
        "31 : 1000",
        delimiter = ':'
    )
    fun `할인 금액을 계산하는 기능 테스트`(dateNumber: Int, expectedDiscount: Int) {
        val totalPrice = TotalPrice(10_000)
        val date = Date(dateNumber)
        val specialEventCalculator = SpecialEventCalculator(date, totalPrice)

        Assertions.assertThat(specialEventCalculator.getDiscount()).isEqualTo(expectedDiscount)
    }
}