package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.Order
import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WeekDayEventCalculatorTest {
    @ParameterizedTest
    @CsvSource(
        "1 : false", "2 : false", "3 : true", "5 : true", "6 : true", "7 : true",
        delimiter = ':'
    )
    fun `이벤트 참여 자격을 판별하는 기능 테스트`(dateNumber: Int, expected: Boolean) {
        val order = Order("타파스-1")
        val totalPrice = TotalPrice(10_000)
        val date = Date(dateNumber)
        val weekDayEventCalculator = WeekDayEventCalculator(order, date, totalPrice)

        Assertions.assertThat(weekDayEventCalculator.isEligibleForEvent()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "초코케이크-1 : 2023",
        "초코케이크-1, 아이스크림 -1 : 4046",
        "타파스-1 : 0",
        "시저샐러드-1 : 0",
        "바비큐립-1, 아이스크림-1 : 2023",
        delimiter = ':'
    )
    fun `할인 금액을 계산하는 기능 테스트`(inputOrders: String, expectedDiscount: Int) {
        val order = Order(inputOrders)
        val totalPrice = TotalPrice(10_000)
        val date = Date(5)
        val weekDayEventCalculator = WeekDayEventCalculator(order, date, totalPrice)

        Assertions.assertThat(weekDayEventCalculator.getDiscount()).isEqualTo(expectedDiscount)
    }
}