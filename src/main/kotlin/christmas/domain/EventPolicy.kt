package christmas.domain

class EventPolicy(
    private val date: Date,
    private val totalPrice: TotalPrice,
) {
    fun isEligibleForChristmasEvent() = isEligibleForEvent() && date.isBeforeDate(25)

    fun isEligibleForWeekDayEvent() = isEligibleForEvent() && date.isWeekDay()

    fun isEligibleForWeekendEvent() = isEligibleForEvent() && !date.isWeekDay()

    fun isEligibleForSpecialEvent() = isEligibleForEvent() && date.isSpecialDay()

    fun isEligibleForFreeMenu() = totalPrice.isMoreThan(120_000)

    private fun isEligibleForEvent() = totalPrice.isMoreThan(10_000)
}