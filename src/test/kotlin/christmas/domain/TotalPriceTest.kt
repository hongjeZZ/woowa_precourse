package christmas.domain

import org.assertj.core.api.Assertions.assertThat
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
        "80_000, true",
        "90_000, true",
        "110_000, false",
        "120_000, false"
    )
    fun `총합 가격이 특정 가격보다 크거나 같은지 판별하는 기능 테스트`(price: Int, expected: Boolean) {
        // When
        val result = totalPrice.isMoreThan(price)

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "10_000, 90_000",
        "25_000, 75_000",
        "0, 100_000"
    )
    fun `가격을 할인하는 기능 테스트`(discountAmount: Int, expectedPrice: Int) {
        // When
        val result = totalPrice.applyDiscount(discountAmount)

        // Then
        assertThat(result).isEqualTo(expectedPrice)
    }
}