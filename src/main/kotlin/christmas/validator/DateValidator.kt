package christmas.validator

class DateValidator {
    fun validate(inputDate: String) {
        val date = parseToInt(inputDate)
        requireValidNumberRange(date)
    }

    private fun parseToInt(inputDate: String): Int {
        return try {
            inputDate.toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        }
    }

    private fun requireValidNumberRange(date: Int) {
        require(date in 1..31) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
    }
}