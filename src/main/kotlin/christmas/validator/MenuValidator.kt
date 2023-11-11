package christmas.validator

import christmas.domain.Menu

class MenuValidator {
    fun validate(menus: List<String>) {
        requireValidMenu(menus)
        requireNotDuplicatedMenu(menus)
        requireNotOnlyBeverage(menus)
    }

    private fun requireValidMenu(menus: List<String>) {
        require(menus.all { menu -> Menu.hasMenu(menu) }) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
    }

    private fun requireNotDuplicatedMenu(menus: List<String>) {
        require(menus.toSet().size == menus.size) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
    }

    private fun requireNotOnlyBeverage(menus: List<String>) {
        require(!menus.all { menu -> Menu.isBeverage(menu) }) { "[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요." }
    }
}