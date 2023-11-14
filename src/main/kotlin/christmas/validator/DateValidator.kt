package christmas.validator

import christmas.validator.Error.INVALID_DATE_ERROR

class DateValidator {
    fun validate(inputDate: String) {
        val date = parseToInt(inputDate)
        requireValidDateRange(date)
    }

    private fun parseToInt(inputDate: String): Int {
        return try {
            inputDate.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_DATE_ERROR)
        }
    }

    private fun requireValidDateRange(date: Int) {
        require(date in DATE_RANGE_START..DATE_RANGE_END) { INVALID_DATE_ERROR }
    }

    companion object {
        private const val DATE_RANGE_START = 1
        private const val DATE_RANGE_END = 31
    }
}