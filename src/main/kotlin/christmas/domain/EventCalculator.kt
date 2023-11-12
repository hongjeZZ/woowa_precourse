package christmas.domain

class EventCalculator(private val eventPolicy: EventPolicy) {
    private var totalDiscount = 0

    fun getFreeMenu(): Order? {
        if (eventPolicy.isEligibleForFreeMenu()) {
            return Order("샴페인-1")
        }
        return null
    }

    fun getFreeMenuDiscount(): Int {
        val freeMenu = getFreeMenu() ?: return 0
        return freeMenu.getTotalPrice()
    }

    fun getChristmasDiscount(date: Date): Int {
        if (eventPolicy.isEligibleForChristmasEvent()) {
            return (date.getMultipliedDate(100)) + 900
        }
        return 0
    }

    fun getWeekDayDiscount(order: Order): Int {
        if (eventPolicy.isEligibleForWeekDayEvent()) {
            return order.getMenuCount("디저트") * 2_023
        }
        return 0
    }

    fun getWeekendDiscount(order: Order): Int {
        if (eventPolicy.isEligibleForWeekendEvent()) {
            return order.getMenuCount("메인") * 2_023
        }
        return 0
    }

    fun getSpecialDiscount(): Int {
        if (eventPolicy.isEligibleForSpecialEvent()) {
            return 1_000
        }
        return 0
    }

    fun getTotalDiscount(order: Order, date: Date): Int {
        calculateTotalDiscount(order, date)
        return totalDiscount
    }

    private fun calculateTotalDiscount(order: Order, date: Date) {
        totalDiscount = getFreeMenuDiscount() + getChristmasDiscount(date) + getWeekDayDiscount(order) +
                getWeekendDiscount(order) + getSpecialDiscount()
    }

    fun getDiscountTotalPrice(totalPrice: TotalPrice): Int {
        return totalPrice.applyDiscount(totalDiscount - getFreeMenuDiscount())
    }

    fun createBadge(): Badge {
        return Badge.getBadge(totalDiscount)
    }
}