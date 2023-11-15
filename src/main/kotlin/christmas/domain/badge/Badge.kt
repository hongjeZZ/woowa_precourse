package christmas.domain.badge

enum class Badge(
    private val badgeName: String,
    private val minimumDiscount: Int,
) {
    SANTA("산타", 20_000),
    TREE("트리", 15_000),
    STAR("별", 5_000),
    NONE("없음",0);

    fun getName() = badgeName

    fun hasRequiredDiscount(totalDiscount: Int) = totalDiscount >= minimumDiscount
}