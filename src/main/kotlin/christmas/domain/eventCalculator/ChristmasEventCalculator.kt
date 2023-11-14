package christmas.domain.eventCalculator

import christmas.domain.Date
import christmas.domain.TotalPrice

class ChristmasEventCalculator(
    private val date: Date, totalPrice: TotalPrice
) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = super.isEligibleForEvent() && date.isBeforeChristmas()

    override fun getDiscount(): Int {
        if (isEligibleForEvent()) {
            return calculateChristmasDiscount()
        }
        return DEFAULT_DISCOUNT_AMOUNT
    }

    private fun calculateChristmasDiscount(): Int {
        return date.getMultipliedDate(DATE_MULTIPLIER) + CHRISTMAS_DISCOUNT_AMOUNT
    }

    companion object {
        private const val DATE_MULTIPLIER = 100
        private const val CHRISTMAS_DISCOUNT_AMOUNT = 900
        private const val DEFAULT_DISCOUNT_AMOUNT = 0
    }
}