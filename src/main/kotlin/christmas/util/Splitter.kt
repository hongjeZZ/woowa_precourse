package christmas.util

object Splitter {
    fun splitByComma(string: String): List<String> {
        return string.split(",")
    }

    fun splitByHyphen(string: String): List<String> {
        return string.split("-")
    }
}