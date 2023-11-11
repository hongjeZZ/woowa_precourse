package christmas.domain


class Promotion(
    private val order: Order,
    private val date: Date,
    private val totalPrice: TotalPrice,
) {
    fun getChristmasDisCount(): Int? {
        if (canReceiveChristmasDiscount()) return date.getDate() * 100 + 900
        return null
    }

    private fun canReceiveChristmasDiscount(): Boolean {
        return date.isBeforeDate(25) && canReceiveEvent()
    }

    fun getWeekDayDisCount(): Int? {
        if (canReceiveWeekDayDiscount()) {
            return order.getMenuCount("디저트") * 2_023
        }
        return null
    }

    private fun canReceiveWeekDayDiscount(): Boolean {
        return canReceiveEvent() && date.isWeekDay()
    }

    fun getWeekendDisCount(): Int? {
        if (canReceiveWeekendDiscount()) {
            return order.getMenuCount("메인") * 2_023
        }
        return null
    }


    private fun canReceiveWeekendDiscount(): Boolean {
        return canReceiveEvent() && !date.isWeekDay()
    }

    fun getFreeMenu(): Order? {
        if (canReceiveFreeMenu()) return Order("샴페인-1")
        return null
    }

    private fun canReceiveFreeMenu(): Boolean {
        return (totalPrice.isMoreThan(120_000))
    }

    private fun canReceiveEvent(): Boolean {
        return totalPrice.isMoreThan(10_000)
    }
}