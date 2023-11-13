package christmas.domain.eventCalculator

import christmas.domain.Order
import christmas.domain.TotalPrice

class GiveawayEventCalculator(private val totalPrice: TotalPrice) : EventCalculator(totalPrice) {
    override fun isEligibleForEvent(): Boolean = totalPrice.isMoreThan(120_000)

    override fun getDiscount(): Int {
        val freeMenu = getGiveawayMenu() ?: return 0
        return freeMenu.getTotalPrice()
    }

    fun getGiveawayMenu(): Order? {
        if (isEligibleForEvent()) {
            return Order("샴페인-1")
        }
        return null
    }
}