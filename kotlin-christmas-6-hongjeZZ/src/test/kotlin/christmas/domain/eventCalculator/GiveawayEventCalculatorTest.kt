package christmas.domain.eventCalculator

import christmas.domain.TotalPrice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GiveawayEventCalculatorTest {
    private lateinit var totalPrice: TotalPrice
    private lateinit var giveawayEventCalculator: GiveawayEventCalculator

    @ParameterizedTest
    @CsvSource(
        "100_000 : false",
        "110_000 : false",
        "120_000 : true",
        "130_000 : true",
        delimiter = ':'
    )
    fun `이벤트 참여 자격을 판별하는 기능 테스트`(price: Int, expected: Boolean) {
        // Given
        totalPrice = TotalPrice(price)
        giveawayEventCalculator = GiveawayEventCalculator(totalPrice)

        // When
        val result = giveawayEventCalculator.isEligibleForEvent()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "100_000 : 0",
        "110_000 : 0",
        "120_000 : 25_000",
        "130_000 : 25_000",
        delimiter = ':'
    )
    fun `할인 금액을 계산하는 기능 테스트`(price: Int, expectedDiscount: Int) {
        // Given
        totalPrice = TotalPrice(price)
        giveawayEventCalculator = GiveawayEventCalculator(totalPrice)

        // When
        val calculatedDiscount = giveawayEventCalculator.getDiscount()

        // Then
        assertThat(calculatedDiscount).isEqualTo(expectedDiscount)
    }
}