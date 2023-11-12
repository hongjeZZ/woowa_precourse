package christmas.domain

import christmas.util.Formatter

class EventManager(eventCalculator: EventCalculator) {
    private lateinit var discountAmounts: List<Pair<String, Int>>
    private var totalDiscount = 0

    init {
        generateDiscounts(eventCalculator)
        calculateTotalDiscount()
    }

    private fun generateDiscounts(eventCalculator: EventCalculator) {
        discountAmounts = listOf(
            "크리스마스 디데이 할인" to eventCalculator.getChristmasDiscount(),
            "평일 할인" to eventCalculator.getWeekDayDiscount(),
            "주말 할인" to eventCalculator.getWeekendDiscount(),
            "특별 할인" to eventCalculator.getSpecialDiscount(),
            "증정 이벤트" to eventCalculator.getFreeMenuDiscount()
        )
    }

    private fun calculateTotalDiscount() {
        totalDiscount = discountAmounts.sumOf { it.second }
    }

    fun issueDiscountReceipt(): String {
        if (totalDiscount == 0) {
            return "없음"
        }
        return buildString {
            discountAmounts.forEach { (eventType, discountAmount) ->
                append(buildReceipt(eventType, discountAmount))
            }
        }.trimEnd()
    }

    private fun buildReceipt(discountType: String, discountAmount: Int): String {
        if (discountAmount == 0) {
            return ""
        }
        return "$discountType: -${Formatter.formatPrice(discountAmount)}\n"
    }

    fun getTotalDiscount(): Int = totalDiscount

    fun getDiscountTotalPrice(totalPrice: TotalPrice, freeMenu: Order?): Int {
        if (freeMenu == null) {
            return totalPrice.applyDiscount(totalDiscount)
        }
        return totalPrice.applyDiscount(totalDiscount - 25000)
    }

    fun createBadge(): Badge {
        return Badge.getBadge(totalDiscount)
    }
}