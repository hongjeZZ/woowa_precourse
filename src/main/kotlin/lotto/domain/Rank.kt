package lotto.domain

enum class Rank(
    private val count: Int,
    private val prize: Int,
    private val message: String,
) {
    NONE(0, 0, ""),
    FIFTH(3, 5_000, "3개 일치 (5,000원) - "),
    FOURTH(4, 50_000, "4개 일치 (50,000원) - "),
    THIRD(5, 1_500_000, "5개 일치 (1,500,000원) - "),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    FIRST(6, 2_000_000_000, "6개 일치 (2,000,000,000원) - ");

    fun getMessage(count: Int?): String {
        return "$message${count}개\n"
    }

    fun getPrize(rank: Rank): Int {
        return rank.prize
    }

    companion object {
        fun checkRank(count: Int, bonusMatch: Boolean): Rank {
            if (count < 3) {
                return NONE
            }
            if (count == 5 && bonusMatch) {
                return SECOND
            }
            for (rank in entries) {
                if (count == rank.count) {
                    return rank
                }
            }
            throw IllegalArgumentException()
        }
    }
}