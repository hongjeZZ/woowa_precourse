package christmas.domain

class EventCalculator(
    private val order: Order,
    private val date: Date,
    private val eventPolicy: EventPolicy,
) {
    fun getFreeMenu(): Order? {
        if (eventPolicy.isEligibleForFreeMenu()) {
            return Order("샴페인-1")
        }
        return null
    }

    fun getFreeMenuDiscount(): Int {
        if (eventPolicy.isEligibleForFreeMenu()) {
            return 25_000
        }
        return 0
    }

    fun getChristmasDiscount(): Int {
        if (eventPolicy.isEligibleForChristmasEvent()) {
            return (date.getMultipliedDate(100)) + 900
        }
        return 0
    }

    fun getWeekDayDiscount(): Int {
        if (eventPolicy.isEligibleForWeekDayEvent()) {
            return order.getMenuCount("디저트") * 2_023
        }
        return 0
    }

    fun getWeekendDiscount(): Int {
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

    fun getTotalDiscount(): Int {
        return getFreeMenuDiscount() + getChristmasDiscount() + getWeekDayDiscount() + getWeekendDiscount() +
                getSpecialDiscount()
    }
}