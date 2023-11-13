package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.Order
import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WeekendEventCalculatorTest {
    @ParameterizedTest
    @CsvSource(
        "1 : true", "2 : true", "3 : false", "5 : false", "6 : false", "7 : false", delimiter = ':'
    )
    fun `이벤트 참여 자격을 판별하는 기능 테스트`(dateNumber: Int, expected: Boolean) {
        val order = Order("타파스-1")
        val totalPrice = TotalPrice(10_000)
        val date = Date(dateNumber)
        val weekendEventCalculator = WeekendEventCalculator(order, date, totalPrice)

        Assertions.assertThat(weekendEventCalculator.isEligibleForEvent()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "바비큐립-1 : 2023",
        "크리스마스파스타-1, 바비큐립-1 : 4046",
        "타파스-1, 시저샐러드-1 : 0",
        "아이스크림-1, 초코케이크-2 : 0",
        "레드와인-1, 초코케이크-1, 시저샐러드-1 : 0",
        delimiter = ':'
    )
    fun `할인 금액을 계산하는 기능 테스트`(inputOrders: String, expectedDiscount: Int) {
        val order = Order(inputOrders)
        val totalPrice = TotalPrice(10_000)
        val date = Date(2)
        val weekendEventCalculator = WeekendEventCalculator(order, date, totalPrice)

        Assertions.assertThat(weekendEventCalculator.getDiscount()).isEqualTo(expectedDiscount)
    }
}