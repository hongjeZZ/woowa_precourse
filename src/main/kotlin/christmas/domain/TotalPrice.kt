package christmas.domain

import christmas.util.Formatter

class TotalPrice(private val _totalPrice: Int) {
    override fun toString(): String {
        return Formatter.formatPrice(_totalPrice)
    }
}