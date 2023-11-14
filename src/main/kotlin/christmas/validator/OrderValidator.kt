package christmas.validator

import christmas.util.Splitter
import christmas.validator.Error.INVALID_ORDER_ERROR

class OrderValidator {
    private val menuValidator = MenuValidator()
    private val menuCountValidator = MenuCountValidator()

    fun validate(inputOrders: String) {
        val orders = Splitter.splitByComma(inputOrders)
        requireValidOrders(orders)
        val (menus, menuCounts) = splitOrders(orders)
        requireValidMenuAndCount(menus, menuCounts)
    }

    private fun requireValidOrders(orders: List<String>) {
        require(orders.all { order ->
            order.isNotBlank()
        }) { INVALID_ORDER_ERROR }
    }

    private fun requireValidMenuAndCount(menus: List<String>, menuCounts: List<Int>) {
        menuValidator.validate(menus)
        menuCountValidator.validate(menuCounts)
    }

    private fun splitOrders(orders: List<String>): Pair<List<String>, List<Int>> {
        return try {
            val ordersSplit = orders.map { Splitter.splitByHyphen(it) }
            val menus = ordersSplit.map { it[0].trim() }
            val menuCounts = ordersSplit.map { it[1].trim().toInt() }
            Pair(menus, menuCounts)

        } catch (e: IndexOutOfBoundsException) {
            throw IllegalArgumentException(INVALID_ORDER_ERROR)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException(INVALID_ORDER_ERROR)
        }
    }
}