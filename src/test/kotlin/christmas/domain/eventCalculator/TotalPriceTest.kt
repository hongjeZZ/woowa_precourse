package christmas.domain.eventCalculator

import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TotalPriceTest {
    private lateinit var totalPrice: TotalPrice

    @BeforeEach
    fun setUp() {
        totalPrice = TotalPrice(100_000)
    }
    @ParameterizedTest
    @CsvSource(
        "80000, true",
        "90000, true",
        "110000, false",
        "120000, false"
    )
    fun `총합 가격이 특정 가격보다 크거나 같은지 판별하는 기능 테스트`(price: Int, expected: Boolean) {
        Assertions.assertThat(totalPrice.isMoreThan(price)).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "10000, 90000",
        "25000, 75000"
    )
    fun `가격을 할인하는 기능 테스트`(discountAmount: Int, expectedPrice: Int) {
        Assertions.assertThat(totalPrice.applyDiscount(discountAmount)).isEqualTo(expectedPrice)
    }
}