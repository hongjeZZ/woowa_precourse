package christmas.domain

class EventPolicy(
    private val date: Date,
    private val totalPrice: TotalPrice,
) {
    fun canReceiveChristmasDiscount() = canReceiveEvent() && date.isBeforeDate(25)

    fun canReceiveWeekDayDiscount() = canReceiveEvent() && date.isWeekDay()

    fun canReceiveWeekendDiscount() = canReceiveEvent() && !date.isWeekDay()

    fun canReceiveSpecialDiscount() = canReceiveEvent() && date.isSpecialDay()

    fun canReceiveFreeMenu() = totalPrice.isMoreThan(120_000)

    private fun canReceiveEvent() = totalPrice.isMoreThan(10_000)
}