package christmas.domain

class TotalPrice(private val _totalPrice: Int) {
    fun isMoreThan(price: Int): Boolean = _totalPrice >= price

    fun applyDiscount(discountAmount: Int): Int = _totalPrice - discountAmount

    fun getPrice(): Int = _totalPrice
}