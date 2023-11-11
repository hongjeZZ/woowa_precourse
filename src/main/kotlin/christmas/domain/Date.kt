package christmas.domain

class Date(private val _date: Int) {
    fun isBeforeDate(date: Int): Boolean = _date <= date

    fun getDate(): Int = _date

    override fun toString(): String {
        return _date.toString()
    }
}