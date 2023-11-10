package christmas.util

import java.text.DecimalFormat

object Formatter {
    private const val DECIMAL_PATTERN = "#,###원"

    fun formatPrice(price: Int): String {
        val decimalFormat = DecimalFormat(DECIMAL_PATTERN)
        return decimalFormat.format(price)
    }
}