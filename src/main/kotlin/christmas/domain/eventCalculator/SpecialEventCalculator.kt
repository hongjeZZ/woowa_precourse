package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.TotalPrice

class SpecialEventCalculator(
    private val date: Date, totalPrice: TotalPrice,
) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = super.isEligibleForEvent() && date.isSpecialDay()

    override fun getDiscount(): Int {
        if (isEligibleForEvent()) {
            return 1_000
        }
        return 0
    }
}