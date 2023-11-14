package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.Order
import christmas.domain.TotalPrice

class WeekendEventCalculator(
    private val order: Order, private val date: Date, totalPrice: TotalPrice
) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = super.isEligibleForEvent() && !date.isWeekDay()

    override fun getDiscount(): Int {
        if (isEligibleForEvent()) {
            return order.getMenuCount(WEEKEND_EVENT_MENU_TYPE) * WEEKEND_DISCOUNT_AMOUNT
        }
        return DEFAULT_DISCOUNT_AMOUNT
    }

    companion object {
        private const val WEEKEND_DISCOUNT_AMOUNT = 2_023
        private const val DEFAULT_DISCOUNT_AMOUNT = 0
        private const val WEEKEND_EVENT_MENU_TYPE = "메인"
    }
}