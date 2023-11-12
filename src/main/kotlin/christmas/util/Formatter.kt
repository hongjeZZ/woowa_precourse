package christmas.util

import java.text.DecimalFormat

object Formatter {
    private const val DECIMAL_PATTERN = "#,###원"
    private const val DISCOUNT_PATTERN = "-$DECIMAL_PATTERN"
    private val decimalFormat = DecimalFormat()
    fun formatPrice(price: Int): String {
        decimalFormat.applyPattern(DECIMAL_PATTERN)
        return decimalFormat.format(price)
    }

    fun formatDiscount(discount: Int): String {
        decimalFormat.applyPattern(DISCOUNT_PATTERN)
        return decimalFormat.format(discount)
    }
}