package christmas.domain.eventCalculator

import christmas.domain.TotalPrice

class GiveawayEventCalculator(private val totalPrice: TotalPrice) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = totalPrice.isMoreThan(120_000)

    override fun getDiscount(): Int {
        if (isEligibleForEvent()) {
            return 25_000
        }
        return 0
    }
}