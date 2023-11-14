package christmas.domain.eventCalculator

import christmas.domain.Menu
import christmas.domain.TotalPrice

class GiveawayEventCalculator(private val totalPrice: TotalPrice) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = totalPrice.isMoreThan(MINIMUM_TOTAL_PRICE_FOR_GIVEAWAY_EVENT)

    override fun getDiscount(): Int {
        if (isEligibleForEvent()) {
            return Menu.getPrice(Menu.CHAMPAGNE)
        }
        return DEFAULT_DISCOUNT_AMOUNT
    }

    companion object {
        private const val MINIMUM_TOTAL_PRICE_FOR_GIVEAWAY_EVENT = 120_000
        private const val DEFAULT_DISCOUNT_AMOUNT = 0
    }
}