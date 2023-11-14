package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.Order
import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class WeekDayEventCalculatorTest {
    private lateinit var order: Order
    private lateinit var totalPrice: TotalPrice
    private lateinit var date: Date
    private lateinit var weekDayEventCalculator: WeekDayEventCalculator

    @ParameterizedTest
    @CsvSource(
        "1 : false", "2 : false", "3 : true", "5 : true", "6 : true", "7 : true",
        delimiter = ':'
    )
    fun `이벤트 참여 자격을 판별하는 기능 테스트`(dateNumber: Int, expected: Boolean) {
        // Given
        order = Order("타파스-1")
        totalPrice = TotalPrice(10_000)
        date = Date(dateNumber)
        weekDayEventCalculator = WeekDayEventCalculator(order, date, totalPrice)

        // When
        val result = weekDayEventCalculator.isEligibleForEvent()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "초코케이크-1 : 2_023",
        "초코케이크-1, 아이스크림-1 : 4_046",
        "타파스-1 : 0",
        "시저샐러드-1 : 0",
        "바비큐립-1, 아이스크림-1 : 2_023",
        delimiter = ':'
    )
    fun `디저트 메뉴 개수에 따라 할인 금액을 계산하는 기능 테스트`(inputOrders: String, expectedDiscount: Int) {
        // Given
        order = Order(inputOrders)
        totalPrice = TotalPrice(10_000)
        date = Date(5)
        weekDayEventCalculator = WeekDayEventCalculator(order, date, totalPrice)

        // When
        val calculatedDiscount = weekDayEventCalculator.getDiscount()

        // Then
        assertThat(calculatedDiscount).isEqualTo(expectedDiscount)
    }
}