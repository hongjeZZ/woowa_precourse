package christmas.domain

enum class Badge(
    private val badgeName: String,
    private val minimumDiscount: Int,
) {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 15_000),
    SANTA("산타", 20_000);

    override fun toString(): String {
        return badgeName
    }

    companion object {
        fun getBadge(totalDiscount: Int): Badge {
            return when {
                totalDiscount >= SANTA.minimumDiscount -> SANTA
                totalDiscount >= TREE.minimumDiscount -> TREE
                totalDiscount >= STAR.minimumDiscount -> STAR
                else -> NONE
            }
        }
    }
}