package christmas.domain

class Date(private val _date: Int) {
    fun isWeekDay(): Boolean = _date % 7 in setOf(0, 3, 4, 5, 6)

    fun isSpecialDay(): Boolean = _date in setOf(3, 10, 17, 24, 25, 31)

    fun isBeforeDate(date: Int): Boolean = _date <= date

    fun getMultipliedDate(multiplier: Int): Int = _date * multiplier

    override fun toString(): String = _date.toString()
}