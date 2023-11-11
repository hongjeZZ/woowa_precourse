package christmas.domain

import christmas.util.Formatter

class TotalPrice(private val _totalPrice: Int) {
    fun isMoreThan(price: Int): Boolean = _totalPrice >= price

    fun applyDiscount(discountAmount: Int): Int = _totalPrice - discountAmount

    override fun toString(): String = Formatter.formatPrice(_totalPrice)
}