package christmas.domain

import christmas.domain.Date
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DateTest {
    @ParameterizedTest
    @CsvSource(
        "1, false",
        "2, false",
        "3, true",
        "4, true",
        "5, true",
        "6, true",
        "7, true"
    )
    fun `날짜가 평일이면 true, 주말이면 false를 반환한다`(dateNumber: Int, expected: Boolean) {
        val date = Date(dateNumber)
        assertThat(date.isWeekDay()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "5, false",
        "15, false",
        "30, false",
        "3, true",
        "10, true",
        "17, true",
        "24, true",
        "25, true",
        "31, true"
    )
    fun `날짜가 특별 날짜면 true, 아니면 false를 반환한다`(dateNumber: Int, expected: Boolean) {
        val date = Date(dateNumber)
        assertThat(date.isSpecialDay()).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "5, false",
        "14, false",
        "15, true",
        "31, true"
    )
    fun `날짜가 특정 날짜 이내면 true, 아니면 false를 반환한다`(dateNumber: Int, expected: Boolean) {
        val date = Date(15)
        assertThat(date.isBeforeDate(dateNumber)).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "100, 100",
        "1500, 1500",
        "9000, 9000"
    )
    fun `날짜와 곱셈 단위를 곱한 값을 반환한다`(multiplier: Int, expectedValue: Int) {
        val date = Date(1)
        assertThat(date.getMultipliedDate(multiplier)).isEqualTo(expectedValue)
    }

    @ParameterizedTest
    @CsvSource(
        "1, 1일",
        "15, 15일",
        "31, 31일"
    )
    fun `날짜를 문자열로 반환한다`(dateNumber: Int,expectedString: String) {
        val date = Date(dateNumber)
        assertThat(date.toString()).isEqualTo(expectedString)
    }
}