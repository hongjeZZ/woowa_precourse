package christmas.validator

class MenuCountValidator {
    fun validate(menuCounts: List<Int>) {
        requireValidCounts(menuCounts)
        requireMenuCountLimit(menuCounts)
    }

    private fun requireValidCounts(menuCounts: List<Int>) {
        require(menuCounts.all { menuCount -> menuCount >= 1 }) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
    }

    private fun requireMenuCountLimit(menuCounts: List<Int>) {
        require(menuCounts.sum() <= 20) { "[ERROR] 메뉴는 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요." }
    }

}