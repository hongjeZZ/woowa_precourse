package christmas.domain

import christmas.util.Splitter

class Order(inputOrders: String) {
    private val _order = mutableMapOf<Menu, Int>()

    init {
        initOrder(inputOrders)
    }

    private fun initOrder(inputOrders: String) {
        val orders = Splitter.splitByComma(inputOrders)

        for (order in orders) {
            val (menuName, count) = Splitter.splitByHyphen(order).map { it.trim() }
            val menu = Menu.getMenu(menuName)
            _order[menu] = count.toInt()
        }
    }

    fun getOrder(): MutableMap<Menu, Int> = _order

    fun getMenuCount(typeName: String): Int {
        var countSum = 0
        for ((menu, count) in _order.entries) {
            if (Menu.getType(menu) == typeName) {
                countSum += count
            }
        }
        return countSum
    }


    fun getTotalPrice(): TotalPrice {
        val totalPrice = _order.entries.sumOf { (menu, count) ->
            Menu.getPrice(menu) * count
        }
        return TotalPrice(totalPrice)
    }
}