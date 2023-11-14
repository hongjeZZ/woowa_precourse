package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.TotalPrice

class ChristmasEventCalculator(
    private val date: Date, totalPrice: TotalPrice
) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = super.isEligibleForEvent() && date.isBeforeChristmas()

    override fun getDiscount(): Int {
        if (isEligibleForEvent()) {
            return (date.getMultipliedDate(100)) + 900
        }
        return 0
    }
}