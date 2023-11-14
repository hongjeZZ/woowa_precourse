package christmas.domain.eventCalculator

import christmas.domain.TotalPrice

abstract class EventCalculator(private val totalPrice: TotalPrice) {
    open fun isEligibleForEvent(): Boolean = totalPrice.isMoreThan(MINIMUM_TOTAL_PRICE_FOR_EVENT)

    abstract fun getDiscount(): Int

    companion object {
        private const val MINIMUM_TOTAL_PRICE_FOR_EVENT = 10_000
    }
}