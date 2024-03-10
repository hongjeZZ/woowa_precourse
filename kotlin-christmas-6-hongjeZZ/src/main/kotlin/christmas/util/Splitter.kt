package christmas.util

object Splitter {
    fun splitByComma(string: String): List<String> = string.split(",")

    fun splitByHyphen(string: String): List<String> = string.split("-")
}