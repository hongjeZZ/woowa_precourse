package christmas.domain

class Order(inputOrder: String) {
    private val _order = mutableMapOf<Menu, Int>()

    init {
        initOrder(inputOrder)
    }

    private fun initOrder(inputOrder: String) {
        val orders = inputOrder.split(",")
        for (order in orders) {
            val (menuName, count) = order.split("-").map { it.trim() }
            val menu = Menu.getMenu(menuName)
            _order[menu] = count.toInt()
        }
    }

    fun getTotalPrice(): Int {
        return _order.entries.sumOf { (menu, count) ->
            Menu.getPrice(menu) * count
        }
    }

    override fun toString(): String {
        return _order.entries.joinToString("\n") { (menu, count) ->
            "${Menu.getName(menu)} ${count}개"
        }
    }
}