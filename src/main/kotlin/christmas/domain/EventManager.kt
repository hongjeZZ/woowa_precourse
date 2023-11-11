package christmas.domain

import christmas.util.Formatter

class EventManager(promotion: Promotion) {
    private val eventDiscounts: List<Pair<String, Int?>>

    init {
        eventDiscounts = listOf(
            "크리스마스 디데이 할인" to promotion.getChristmasDiscount(),
            "평일 할인" to promotion.getWeekDayDiscount(),
            "주말 할인" to promotion.getWeekendDiscount(),
            "특별 할인" to promotion.getSpecialDiscount(),
            "증정 이벤트" to promotion.getFreeMenuDiscount()
        )
    }

    fun issueDiscountReceipt(): String {
        if (eventDiscounts.all { it.second == null }) return "없음"

        return buildString {
            eventDiscounts.forEach { (eventType, discountAmount) ->
                append(buildDiscount(eventType, discountAmount))
            }
        }
    }

    private fun buildDiscount(discountType: String, discountAmount: Int?): String {
        if (discountAmount == null || discountAmount == 0) {
            return ""
        }
        return "$discountType: -${Formatter.formatPrice(discountAmount)}\n"
    }

    fun getTotalDiscount(): Int = eventDiscounts.sumOf { it.second ?: 0 }
}