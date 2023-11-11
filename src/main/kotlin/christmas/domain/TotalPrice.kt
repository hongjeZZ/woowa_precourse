package christmas.domain

import christmas.util.Formatter

class TotalPrice(private val _totalPrice: Int) {
    fun isMoreThan(price: Int): Boolean = _totalPrice >= price

    override fun toString(): String {
        return Formatter.formatPrice(_totalPrice)
    }
}