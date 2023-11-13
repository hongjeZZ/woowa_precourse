package christmas.domain

import christmas.domain.eventCalculator.*

class EventManager(order: Order, date: Date, totalPrice: TotalPrice) {
    private val eventCalculators = setCalculators(order, date, totalPrice)
    private val giveawayEventCalculator = GiveawayEventCalculator(totalPrice)
    private var totalDiscount = 0

    init {
        calculateTotalDiscount()
    }

    private fun setCalculators(order: Order, date: Date, totalPrice: TotalPrice): List<EventCalculator> {
        val calculators = mutableListOf<EventCalculator>()
        calculators.add(ChristmasEventCalculator(date, totalPrice))
        calculators.add(WeekDayEventCalculator(order, date, totalPrice))
        calculators.add(WeekendEventCalculator(order, date, totalPrice))
        calculators.add(SpecialEventCalculator(date, totalPrice))
        return calculators.toList()
    }

    private fun calculateTotalDiscount() {
        totalDiscount = getDiscounts().sum()
    }

    fun getDiscounts(): List<Int> {
        return eventCalculators.map { it.getDiscount() } + giveawayEventCalculator.getDiscount()
    }

    fun getGiveawayMenu(): Order? = giveawayEventCalculator.getGiveawayMenu()

    fun getTotalDiscount(): Int = totalDiscount

    fun getFinalPrice(totalPrice: TotalPrice): Int {
        return totalPrice.applyDiscount(totalDiscount - giveawayEventCalculator.getDiscount())
    }

    fun createBadge(): Badge = Badge.getBadge(totalDiscount)
}