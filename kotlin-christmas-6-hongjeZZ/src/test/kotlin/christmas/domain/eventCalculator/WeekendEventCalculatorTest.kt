package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.Order
import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WeekendEventCalculatorTest {
    private lateinit var order: Order
    private lateinit var totalPrice: TotalPrice
    private lateinit var date: Date
    private lateinit var weekendEventCalculator: WeekendEventCalculator

    @ParameterizedTest
    @CsvSource(
        "1 : true", "2 : true", "3 : false", "5 : false", "6 : false", "7 : false", delimiter = ':'
    )
    fun `이벤트 참여 자격을 판별하는 기능 테스트`(dateNumber: Int, expected: Boolean) {
        // Given
        order = Order("타파스-1")
        totalPrice = TotalPrice(10_000)
        date = Date(dateNumber)
        weekendEventCalculator = WeekendEventCalculator(order, date, totalPrice)

        // When
        val result = weekendEventCalculator.isEligibleForEvent()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "바비큐립-1 : 2_023",
        "크리스마스파스타-1, 바비큐립-1 : 4_046",
        "타파스-1, 시저샐러드-1 : 0",
        "아이스크림-1, 초코케이크-2 : 0",
        "레드와인-1, 초코케이크-1, 시저샐러드-1 : 0",
        delimiter = ':'
    )
    fun `메인 메뉴 개수에 따라 할인 금액을 계산하는 기능 테스트`(inputOrders: String, expectedDiscount: Int) {
        // Given
        order = Order(inputOrders)
        totalPrice = TotalPrice(10_000)
        date = Date(2)
        weekendEventCalculator = WeekendEventCalculator(order, date, totalPrice)

        // When
        val calculatedDiscount = weekendEventCalculator.getDiscount()

        // Then
        assertThat(calculatedDiscount).isEqualTo(expectedDiscount)
    }
}