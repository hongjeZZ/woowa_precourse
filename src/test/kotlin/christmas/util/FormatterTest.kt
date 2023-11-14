package christmas.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class FormatterTest {
    @ParameterizedTest
    @CsvSource(
        "0 : 0원",
        "1_000 : 1,000원",
        "15_000 : 15,000원",
        "123_456_789 : 123,456,789원",
        delimiter = ':'
    )
    fun `가격 포맷 기능 테스트`(price: Int, expectedString: String) {
        // When
        val result = Formatter.formatPrice(price)

        // Then
        assertThat(result).isEqualTo(expectedString)
    }

    @ParameterizedTest
    @CsvSource(
        "0 : -0원",
        "1_000 : -1,000원",
        "15_000 : -15,000원",
        "123_456_789 : -123,456,789원",
        delimiter = ':'
    )
    fun `할인 금액 포맷 기능 테스트`(discount: Int, expectedString: String) {
        // When
        val result = Formatter.formatDiscount(discount)

        // Then
        assertThat(result).isEqualTo(expectedString)
    }
}