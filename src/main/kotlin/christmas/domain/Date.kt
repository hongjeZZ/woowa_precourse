package christmas.domain

class Date(private val _date: Int) {
    fun isWeekDay(): Boolean = (_date % DAYS_IN_WEEK) in WEEKDAYS

    fun isSpecialDay(): Boolean = _date in SPECIAL_DAYS

    fun isBeforeChristmas(): Boolean = _date <= CHRISTMAS_DAY

    fun getMultipliedDate(multiplier: Int): Int = _date * multiplier

    override fun toString(): String = _date.toString() + DATE_UNIT

    companion object {
        private const val DAYS_IN_WEEK = 7
        private const val CHRISTMAS_DAY = 25
        private val WEEKDAYS = setOf(0, 3, 4, 5, 6)
        private val SPECIAL_DAYS = setOf(3, 10, 17, 24, 25, 31)
        private const val DATE_UNIT = "일"
    }
}