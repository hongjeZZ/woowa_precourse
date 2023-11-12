package christmas.domain

class EventManager {
    fun getDiscountTotalPrice(totalPrice: TotalPrice, totalDiscount: Int, freeMenu: Order?): Int {
        if (freeMenu == null) {
            return totalPrice.applyDiscount(totalDiscount)
        }
        return totalPrice.applyDiscount(totalDiscount - 25000)
    }

    fun createBadge(totalDiscount: Int): Badge {
        return Badge.getBadge(totalDiscount)
    }
}