package christmas.domain.eventCalculator

import christmas.domain.TotalPrice

abstract class EventCalculator(private val totalPrice: TotalPrice) {
    open fun isEligibleForEvent(): Boolean = totalPrice.isMoreThan(10_000)

    abstract fun getDiscount(): Int
}