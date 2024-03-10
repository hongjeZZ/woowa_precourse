package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DateTest {
    private lateinit var date: Date
    @ParameterizedTest
    @CsvSource(
        "1, false",
        "2, false",
        "3, true",
        "4, true",
        "17, true",
        "22, false",
        "31, true"
    )
    fun `날짜가 평일이면 true, 주말이면 false를 반환한다`(dateNumber: Int, expected: Boolean) {
        // Given
        date = Date(dateNumber)

        // When
        val result = date.isWeekDay()

        // Then
        assertThat(result).isEqualTo(expected)
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
        // Given
        date = Date(dateNumber)

        // When
        val result = date.isSpecialDay()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "5, true",
        "14, true",
        "26, false",
        "31, false"
    )
    fun `날짜가 25일 이전이면 true, 아니면 false를 반환한다`(dateNumber: Int, expected: Boolean) {
        // Given
        date = Date(dateNumber)

        // When
        val result = date.isBeforeChristmas()

        // Then
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "100, 100",
        "1_500, 1_500",
        "9_000, 9_000"
    )
    fun `날짜와 곱셈 단위를 곱한 값을 반환한다`(multiplier: Int, expectedValue: Int) {
        // Given
        date = Date(1)

        // When
        val multipliedDate = date.getMultipliedDate(multiplier)

        // Then
        assertThat(multipliedDate).isEqualTo(expectedValue)
    }

    @ParameterizedTest
    @CsvSource(
        "1, 1일",
        "15, 15일",
        "31, 31일"
    )
    fun `날짜를 문자열로 반환한다`(dateNumber: Int,expectedString: String) {
        // Given
        date = Date(dateNumber)

        // When
        val dateString = date.toString()

        // Then
        assertThat(dateString).isEqualTo(expectedString)
    }
}