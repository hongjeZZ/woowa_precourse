package christmas.validator

import christmas.domain.Menu
import christmas.validator.Error.INVALID_ORDER_ERROR
import christmas.validator.Error.ONLY_BEVERAGE_MENU_ERROR

class MenuValidator {
    fun validate(menus: List<String>) {
        requireValidMenu(menus)
        requireNoDuplicatedMenu(menus)
        requireNotOnlyBeverage(menus)
    }

    private fun requireValidMenu(menus: List<String>) {
        require(menus.all { menu ->
            Menu.hasMenu(menu)
        }) { INVALID_ORDER_ERROR }
    }

    private fun requireNoDuplicatedMenu(menus: List<String>) {
        require(menus.toSet().size == menus.size) { INVALID_ORDER_ERROR }
    }

    private fun requireNotOnlyBeverage(menus: List<String>) {
        require(!menus.all { menu ->
            Menu.isBeverage(menu)
        }) { ONLY_BEVERAGE_MENU_ERROR }
    }
}