package christmas.domain.badge

class BadgeManager {
    fun createBadge(totalDiscount: Int): Badge {
        return Badge.entries.first { badge ->
            badge.hasRequiredDiscount(totalDiscount)
        }
    }
}