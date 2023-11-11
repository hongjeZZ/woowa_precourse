package christmas.domain

class EventCalculator(
    private val order: Order,
    private val date: Date,
    private val eventPolicy: EventPolicy
) {
    fun getChristmasDiscount(): Int? {
        if (eventPolicy.canReceiveChristmasDiscount()) {
            return date.getDate() * 100 + 900
        }
        return null
    }

    fun getWeekDayDiscount(): Int? {
        if (eventPolicy.canReceiveWeekDayDiscount()) {
            return order.getMenuCount("디저트") * 2_023
        }
        return null
    }

    fun getWeekendDiscount(): Int? {
        if (eventPolicy.canReceiveWeekendDiscount()) {
            return order.getMenuCount("메인") * 2_023
        }
        return null
    }

    fun getSpecialDiscount(): Int? {
        if (eventPolicy.canReceiveSpecialDiscount()) {
            return 1_000
        }
        return null
    }

    fun getFreeMenuDiscount(): Int? {
        if (eventPolicy.canReceiveFreeMenu()) {
            return 25_000
        }
        return null
    }


    fun getFreeMenu(): Order? {
        if (eventPolicy.canReceiveFreeMenu()) {
            return Order("샴페인-1")
        }
        return null
    }
}