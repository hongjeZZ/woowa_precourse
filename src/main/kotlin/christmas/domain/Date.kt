package christmas.domain

class Date(private val _date: Int) {
    fun isWeekDay(): Boolean {
        val dayOfWeek = (_date % 7) + 1
        return dayOfWeek in 2..6
    }

    fun isSpecialDay(): Boolean {
        return _date in setOf(3, 10, 17, 24, 25, 31)
    }

    fun isBeforeDate(date: Int): Boolean = _date <= date

    fun getDate(): Int = _date

    override fun toString(): String {
        return _date.toString()
    }
}