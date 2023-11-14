package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BadgeTest {
    @ParameterizedTest
    @CsvSource(
        "0, NONE",
        "4_999, NONE",
        "5_000, STAR",
        "14_999, STAR",
        "15_000, TREE",
        "19_999, TREE",
        "20_000, SANTA",
        "25_000, SANTA"
    )
    fun `뱃지를 반환하는 기능 테스트`(
        totalDiscount: Int,
        expectedBadge: Badge
    ) {
        // When
        val resultBadge = Badge.getBadge(totalDiscount)

        // Then
        assertThat(resultBadge).isEqualTo(expectedBadge)
    }

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
        val resultName = badge.getName(badge)

        // Then
        assertThat(resultName).isEqualTo(expectedName)
    }
}