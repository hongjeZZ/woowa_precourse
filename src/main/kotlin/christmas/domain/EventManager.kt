package christmas.domain

import christmas.util.Formatter

class EventManager(promotion: Promotion) {
    private lateinit var eventDiscounts: List<Pair<String, Int?>>
    private var totalDiscount = 0

    init {
        generateEventDiscounts(promotion)
        calculateTotalDiscount()
    }

    private fun generateEventDiscounts(promotion: Promotion) {
        eventDiscounts = listOf(
            "크리스마스 디데이 할인" to promotion.getChristmasDiscount(),
            "평일 할인" to promotion.getWeekDayDiscount(),
            "주말 할인" to promotion.getWeekendDiscount(),
            "특별 할인" to promotion.getSpecialDiscount(),
            "증정 이벤트" to promotion.getFreeMenuDiscount()
        )
    }

    private fun calculateTotalDiscount() {
        totalDiscount = eventDiscounts.sumOf { it.second ?: 0 }
    }

    fun getTotalDiscount(): Int = totalDiscount

    fun issueDiscountReceipt(): String {
        if (eventDiscounts.all { it.second == null }) {
            return "없음"
        }
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

    fun getDiscountTotalPrice(totalPrice: TotalPrice, freeMenu: Order?): Int {
        if (freeMenu == null) {
            return totalPrice.getDiscountPrice(totalDiscount)
        }
        return totalPrice.getDiscountPrice(totalDiscount - 25000)
    }
}