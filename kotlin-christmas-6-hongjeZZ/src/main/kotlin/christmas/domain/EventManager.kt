package christmas.domain

import christmas.domain.eventCalculator.ChristmasEventCalculator
import christmas.domain.eventCalculator.EventCalculator
import christmas.domain.eventCalculator.GiveawayEventCalculator
import christmas.domain.eventCalculator.SpecialEventCalculator
import christmas.domain.eventCalculator.WeekDayEventCalculator
import christmas.domain.eventCalculator.WeekendEventCalculator

class EventManager(order: Order, date: Date, totalPrice: TotalPrice) {
    private val eventCalculators = createCalculators(order, date, totalPrice)
    private val giveawayEventCalculator = GiveawayEventCalculator(totalPrice)

    private fun createCalculators(order: Order, date: Date, totalPrice: TotalPrice): List<EventCalculator> {
        val calculators = mutableListOf<EventCalculator>()
        calculators.add(ChristmasEventCalculator(date, totalPrice))
        calculators.add(WeekDayEventCalculator(order, date, totalPrice))
        calculators.add(WeekendEventCalculator(order, date, totalPrice))
        calculators.add(SpecialEventCalculator(date, totalPrice))
        return calculators.toList()
    }

    fun getDiscounts(): List<Int> {
        return eventCalculators.map { eventCalculator ->
            eventCalculator.getDiscount()
        } + giveawayEventCalculator.getDiscount()
    }

    fun getGiveawayMenu(): Order? {
        if (giveawayEventCalculator.isEligibleForEvent()) {
            return Order("샴페인-1")
        }
        return null
    }

    fun getTotalDiscount(): Int = getDiscounts().sum()

    fun getFinalPrice(totalPrice: TotalPrice): Int {
        val totalDiscount = getTotalDiscount()
        return totalPrice.applyDiscount(totalDiscount - giveawayEventCalculator.getDiscount())
    }
}