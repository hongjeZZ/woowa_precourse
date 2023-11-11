package christmas.domain


class Promotion {
    fun getFreeMenu(totalPrice: TotalPrice): Order? {
        if (canReceiveFreeMenu(totalPrice)) return Order("샴페인-1")
        return null
    }

    private fun canReceiveFreeMenu(totalPrice: TotalPrice): Boolean {
        return (totalPrice.isMoreThan(120_000))
    }
}