package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.TotalPrice

class SpecialEventCalculator(
    private val date: Date, totalPrice: TotalPrice,
) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = super.isEligibleForEvent() && date.isSpecialDay()

    override fun getDiscount(): Int {
        if (isEligibleForEvent()) {
            return SPECIAL_DISCOUNT_AMOUNT
        }
        return DEFAULT_DISCOUNT_AMOUNT
    }

    companion object {
        private const val SPECIAL_DISCOUNT_AMOUNT = 1_000
        private const val DEFAULT_DISCOUNT_AMOUNT = 0
    }
}