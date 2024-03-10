package christmas.validator

import christmas.validator.Error.INVALID_MENU_LIMIT_ERROR
import christmas.validator.Error.INVALID_ORDER_ERROR

class MenuCountValidator {
    fun validate(menuCounts: List<Int>) {
        requireValidCounts(menuCounts)
        requireMenuCountLimit(menuCounts)
    }

    private fun requireValidCounts(menuCounts: List<Int>) {
        require(menuCounts.all { menuCount ->
            menuCount >= MINIMUM_MENU_COUNT
        }) { INVALID_ORDER_ERROR }
    }

    private fun requireMenuCountLimit(menuCounts: List<Int>) {
        require(menuCounts.sum() <= MAXIMUM_SUM_OF_MENU_COUNT) { INVALID_MENU_LIMIT_ERROR }
    }

    companion object {
        private const val MINIMUM_MENU_COUNT = 1
        private const val MAXIMUM_SUM_OF_MENU_COUNT = 20
    }
}