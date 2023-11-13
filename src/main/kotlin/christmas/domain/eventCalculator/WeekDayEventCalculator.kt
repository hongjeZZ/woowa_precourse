package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.Order
import christmas.domain.TotalPrice

class WeekDayEventCalculator(
    private val order: Order, private val date: Date, totalPrice: TotalPrice
) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = super.isEligibleForEvent() && date.isWeekDay()

    override fun getDiscount(): Int {
        if (isEligibleForEvent()) {
            return order.getMenuCount("디저트") * 2_023
        }
        return 0
    }
}