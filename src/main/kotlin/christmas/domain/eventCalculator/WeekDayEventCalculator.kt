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
            return order.getMenuCount(WEEKDAY_EVENT_MENU_TYPE) * WEEKDAY_DISCOUNT
        }
        return DEFAULT_DISCOUNT
    }

    companion object {
        private const val WEEKDAY_DISCOUNT = 2_023
        private const val DEFAULT_DISCOUNT = 0
        private const val WEEKDAY_EVENT_MENU_TYPE = "디저트"
    }
}