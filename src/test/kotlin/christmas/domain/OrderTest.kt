package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class OrderTest {
    private lateinit var order: Order

    @BeforeEach
    fun setUp() {
        val inputOrders = "양송이수프-2,타파스-3,레드와인-1"
        order = Order(inputOrders)
    }

    @Test
    fun `Order 객체 초기화 기능 테스트`() {
        val expectedOrder = mutableMapOf(
            Menu.MUSHROOM_SOUP to 2, Menu.TAPAS to 3, Menu.RED_WINE to 1
        )

        assertThat(order.getOrder()).isEqualTo(expectedOrder)
    }

    @ParameterizedTest
    @CsvSource(
        "에피타이저, 5", "음료, 1", "디저트, 0", "메인, 0"
    )
    fun `특정 타입의 메뉴 개수를 반환하는 기능 테스트`(typeName: String, expectedCount: Int) {
        assertThat(order.getMenuCount(typeName)).isEqualTo(expectedCount)
    }

    @ParameterizedTest
    @CsvSource(
        "양송이수프-2,타파스-3,레드와인-1 : 88500", "타파스-2,제로콜라-3 : 20000", "티본스테이크-3,레드와인-4,시저샐러드-2 : 421000",
        delimiter = ':'
    )
    fun `총 주문 금액을 계산하는 기능 테스트`(inputOrders: String, expectedPrice: Int) {
        order = Order(inputOrders)

        assertThat(order.getTotalPrice()).isEqualTo(expectedPrice)
    }
}