package christmas.validator

class OrderValidator {
    private val menuValidator = MenuValidator()
    private val menuCountValidator = MenuCountValidator()

    fun validate(inputOrders: String) {
        val orders = inputOrders.split(",")
        requireValidOrders(orders)

        val (menus, menuCounts) = splitOrders(orders)
        menusValidator.validate(menus)
        menuCountsValidator.validate(menuCounts)
    }

    private fun requireValidOrders(orders: List<String>) {
        require(orders.all { it.isNotBlank() }) { "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." }
    }

    private fun splitOrders(orders: List<String>): Pair<List<String>, List<Int>> {
        return try {
            val ordersSplit = orders.map { it.split("-") }
            val menus = ordersSplit.map { it[0].trim() }
            val menuCounts = ordersSplit.map { it[1].trim().toInt() }
            Pair(menus, menuCounts)

        } catch (e: IndexOutOfBoundsException) {
            throw IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }
}