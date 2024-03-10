package christmas.domain

import christmas.domain.eventCalculator.ChristmasEventCalculator
import christmas.domain.eventCalculator.EventCalculator
import christmas.domain.eventCalculator.GiveawayEventCalculator
import christmas.domain.eventCalculator.SpecialEventCalculator
import christmas.domain.eventCalculator.WeekDayEventCalculator
import christmas.domain.eventCalculator.WeekendEventCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class EventManagerTest {
    private lateinit var order: Order
    private lateinit var date: Date
    private lateinit var totalPrice: TotalPrice
    private lateinit var eventManager: EventManager
    private lateinit var calculators: List<EventCalculator>

    private fun initEventManager(orderInput: String, dateNumber: Int) {
        order = Order(orderInput)
        date = Date(dateNumber)
        totalPrice = TotalPrice(order.getTotalPrice())
        eventManager = EventManager(order, date, totalPrice)
    }

    private fun initCalculators(order: Order, date: Date, totalPrice: TotalPrice) {
        calculators = listOf(
            ChristmasEventCalculator(date, totalPrice),
            WeekDayEventCalculator(order, date, totalPrice),
            WeekendEventCalculator(order, date, totalPrice),
            SpecialEventCalculator(date, totalPrice),
            GiveawayEventCalculator(totalPrice)
        )
    }

    @ParameterizedTest(name = "{index} => 주문: {0}, 날짜: {1}")
    @CsvSource(
        TEST_ORDER_AND_DATE_1,
        TEST_ORDER_AND_DATE_2,
        TEST_ORDER_AND_DATE_3,
        TEST_ORDER_AND_DATE_4,
        TEST_ORDER_AND_DATE_5,
        TEST_ORDER_AND_DATE_6,
        delimiter = ':'
    )
    fun `할인 금액 리스트 반환 기능 테스트`(
        orderInput: String,
        dateNumber: Int
    ) {
        // Given
        initEventManager(orderInput, dateNumber)
        initCalculators(order, date, totalPrice)
        val expectedDiscounts = calculators.map { it.getDiscount() }

        // When
        val resultDiscounts = eventManager.getDiscounts()

        // Then
        assertThat(resultDiscounts).isEqualTo(expectedDiscounts)
    }

    @ParameterizedTest(name = "{index} => 주문: {0}, 날짜: {1}")
    @CsvSource(
        TEST_ORDER_AND_DATE_1,
        TEST_ORDER_AND_DATE_2,
        TEST_ORDER_AND_DATE_3,
        TEST_ORDER_AND_DATE_4,
        TEST_ORDER_AND_DATE_5,
        TEST_ORDER_AND_DATE_6,
        delimiter = ':'
    )
    fun `총 할인 금액 반환 기능 테스트`(
        orderInput: String,
        dateNumber: Int
    ) {
        // Given
        initEventManager(orderInput, dateNumber)
        initCalculators(order, date, totalPrice)
        val expectedTotalDiscount = calculators.sumOf { it.getDiscount() }

        // When
        val resultTotalDiscount = eventManager.getTotalDiscount()

        // Then
        assertThat(resultTotalDiscount).isEqualTo(expectedTotalDiscount)
    }

    @ParameterizedTest(name = "{index} => 주문: {0}, 날짜: {1}")
    @CsvSource(
        TEST_ORDER_AND_DATE_1,
        TEST_ORDER_AND_DATE_2,
        TEST_ORDER_AND_DATE_3,
        TEST_ORDER_AND_DATE_4,
        TEST_ORDER_AND_DATE_5,
        TEST_ORDER_AND_DATE_6,
        delimiter = ':'
    )
    fun `최종 금액 반환 기능 테스트`(
        orderInput: String,
        dateNumber: Int
    ) {
        // Given
        initEventManager(orderInput, dateNumber)
        val giveawayEventCalculator = GiveawayEventCalculator(totalPrice)
        val expectedFinalPrice =
            totalPrice.getPrice() - (eventManager.getTotalDiscount() - (giveawayEventCalculator.getDiscount()))

        // When
        val resultFinalPrice = eventManager.getFinalPrice(totalPrice)

        // Then
        assertThat(resultFinalPrice).isEqualTo(expectedFinalPrice)
    }


    @ParameterizedTest
    @CsvSource(
        "티본스테이크-2,양송이수프-2,레드와인-1",
        "타파스-2,바비큐립-2,샴페인-1",
        "크리스마스파스타-2,해산물파스타-2,샴페인-2",
        delimiter = ':'
    )
    fun `증정 메뉴 반환하는 기능 테스트 - 증정 메뉴를 반환하는 경우`(orderInput: String) {
        // Given
        initEventManager(orderInput, 1)

        // When
        val freeMenu = eventManager.getGiveawayMenu()

        // Then
        val expectedFreeMenu = Order("샴페인-1").getOrder()
        assertThat(freeMenu?.getOrder()).isEqualTo(expectedFreeMenu)
    }

    @ParameterizedTest
    @CsvSource(
        "타파스-2,아이스크림-2",
        "시저샐러드-2,바비큐립-1,제로콜라-2",
        "크리스마스파스타-2,샴페인-2",
        delimiter = ':'
    )
    fun `증정 메뉴 반환하는 기능 테스트 - null을 반환하는 경우`(orderInput: String) {
        // Given
        initEventManager(orderInput, 1)
        totalPrice = TotalPrice(order.getTotalPrice())
        eventManager = EventManager(order, date, totalPrice)

        // When
        val freeMenu = eventManager.getGiveawayMenu()

        // Then
        assertThat(freeMenu).isNull()
    }

    companion object {
        private const val TEST_ORDER_AND_DATE_1 = "티본스테이크-2,양송이수프-2,타파스-3,레드와인-2 : 25"
        private const val TEST_ORDER_AND_DATE_2 = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1 : 26"
        private const val TEST_ORDER_AND_DATE_3 = "양송이수프-1,타파스-1,제로콜라-1 : 2"
        private const val TEST_ORDER_AND_DATE_4 = "바비큐립-1,크리스마스파스타-1,초코케이크-1 : 11"
        private const val TEST_ORDER_AND_DATE_5 = "해산물파스타-3,타파스-2,제로콜라-5 : 15"
        private const val TEST_ORDER_AND_DATE_6 = "초코케이크-1,제로콜라-1,샴페인-1 : 31"
    }
}