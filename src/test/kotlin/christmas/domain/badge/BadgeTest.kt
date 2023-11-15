package christmas.domain.badge

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BadgeTest {
    @ParameterizedTest
    @CsvSource(
        "NONE, 없음",
        "STAR, 별",
        "TREE, 트리",
        "SANTA, 산타",
    )
    fun `뱃지 이름을 반환하는 기능 테스트`(
        badge: Badge,
        expectedName: String
    ) {
        // When
        val resultName = badge.getName()

        // Then
        assertThat(resultName).isEqualTo(expectedName)
    }

    @ParameterizedTest
    @CsvSource(
        "SANTA, 20001, true",
        "SANTA, 20000, true",
        "TREE, 15001, true",
        "TREE, 15000, true",
        "STAR, 5001, true",
        "STAR, 5000, true",
        "NONE, 0, true",
        "NONE, 1, true"
    )
    fun `필요한 할인 금액을 판단하는 기능 테스트`(
        badge: Badge,
        totalDiscount: Int,
        expected: Boolean
    ) {
        // when
        val result = badge.hasRequiredDiscount(totalDiscount)

        // then
        assertThat(result).isEqualTo(expected)
    }
}