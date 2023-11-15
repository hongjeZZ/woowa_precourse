package christmas.domain.badge

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BadgeManagerTest {
    @ParameterizedTest
    @CsvSource(
        "SANTA, 50_000",
        "SANTA, 20_001",
        "SANTA, 20_000",
        "TREE, 15_001",
        "TREE, 15_000",
        "STAR, 5_001",
        "STAR, 5_000",
        "NONE, 4_999",
        "NONE, 0"
    )
    fun `총 할인 금액에 따라 뱃지를 반환하는 기능 테스트`(
        expectedBadge: Badge,
        totalDiscount: Int
    ) {
        // given
        val badgeManager = BadgeManager()

        // when
        val resultBadge = badgeManager.createBadge(totalDiscount)

        // then
        assertThat(resultBadge).isEqualTo(expectedBadge)
    }
}