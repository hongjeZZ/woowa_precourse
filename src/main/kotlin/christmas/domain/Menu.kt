package christmas.domain

enum class Menu(
    private val menuName: String,
    private val price: Int,
    private val type: String
) {
    MUSHROOM_SOUP("양송이수프", 6_000, "에피타이저"),
    TAPAS("타파스", 5_500, "에피타이저"),
    CAESAR_SALAD("시저샐러드", 8_000, "에피타이저"),
    T_BONE_STEAK("티본스테이크", 55_000, "메인"),
    BBQ_RIBS("바비큐립", 54_000, "메인"),
    SEAFOOD_PASTA("해산물파스타", 35_000, "메인"),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, "메인"),
    CHOCO_CAKE("초코케이크", 15_000, "디저트"),
    ICE_CREAM("아이스크림", 5_000, "디저트"),
    ZERO_COLA("제로콜라", 3_000, "음료"),
    RED_WINE("레드와인", 60_000, "음료"),
    CHAMPAGNE("샴페인", 25_000, "음료");

    companion object {
        fun getType(menu: Menu): String {
            return menu.type
        }

        fun getPrice(menu: Menu): Int {
            return menu.price
        }

        fun getName(menu: Menu): String {
            return menu.menuName
        }

        fun getMenu(name: String): Menu {
            return entries.find { it.menuName == name }!!
        }

        fun hasMenu(name: String): Boolean {
            return entries.any { it.menuName == name }
        }

        fun isBeverage(name: String): Boolean {
            return getMenu(name).type == "음료"
        }
    }
}