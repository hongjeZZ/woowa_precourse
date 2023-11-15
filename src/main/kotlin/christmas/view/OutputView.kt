package christmas.view

import christmas.domain.badge.Badge
import christmas.domain.Date
import christmas.domain.Order
import christmas.domain.TotalPrice
import christmas.util.Formatter

class OutputView {
    fun printProgramStartMessage() {
        println(PROGRAM_START_MESSAGE)
    }

    fun printBenefitPreview(date: Date) {
        println(EVENT_PREVIEW_MESSAGE.format(date))
    }

    fun printOrderDetails(order: Order) {
        printBlankLine()
        println(ORDER_HEADER)
        printOrder(order)
    }

    private fun printOrder(order: Order) {
        order.getOrder().entries.forEach { (menu, count) ->
            println("${menu.getName(menu)} ${count}개")
        }
    }

    fun printTotalPrice(totalPrice: TotalPrice) {
        printBlankLine()
        println(TOTAL_PRICE_HEADER)
        println(Formatter.formatPrice(totalPrice.getPrice()))
    }

    fun printGiveawayMenu(giveawayMenu: Order?) {
        printBlankLine()
        println(GIVEAWAY_MENU_HEADER)
        giveawayMenu?.let { printOrder(it) } ?: printNone()
    }

    fun printDiscountDetails(discounts: List<Int>) {
        printBlankLine()
        println(DISCOUNT_DETAILS_HEADER)
        if (discounts.sum() != 0) {
            printDiscount(DISCOUNT_CHRISTMAS, discounts[0])
            printDiscount(DISCOUNT_WEEKDAY, discounts[1])
            printDiscount(DISCOUNT_WEEKEND, discounts[2])
            printDiscount(DISCOUNT_SPECIAL, discounts[3])
            printDiscount(DISCOUNT_GIVEAWAY, discounts[4])
            return
        }
        printNone()
    }

    private fun printNone() {
        println(NONE_MESSAGE)
    }

    private fun printDiscount(name: String, discount: Int) {
        if (discount != 0) {
            println("$name: ${Formatter.formatDiscount(discount)}")
        }
    }

    fun printTotalDiscount(totalDiscount: Int) {
        printBlankLine()
        println(TOTAL_DISCOUNT_HEADER)
        if (totalDiscount != 0) {
            println(Formatter.formatDiscount(totalDiscount))
            return
        }
        println(Formatter.formatPrice(totalDiscount))
    }

    fun printFinalPrice(finalPrice: Int) {
        printBlankLine()
        println(FINAL_PRICE_HEADER)
        println(Formatter.formatPrice(finalPrice))
    }

    fun printBadge(badge: Badge) {
        printBlankLine()
        println(BADGE_HEADER)
        print(badge.getName())
    }

    private fun printBlankLine() {
        println()
    }

    companion object {
        private const val PROGRAM_START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
        private const val EVENT_PREVIEW_MESSAGE = "12월 %s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
        private const val ORDER_HEADER = "<주문 메뉴>"
        private const val TOTAL_PRICE_HEADER = "<할인 전 총주문 금액>"
        private const val GIVEAWAY_MENU_HEADER = "<증정 메뉴>"
        private const val DISCOUNT_DETAILS_HEADER = "<혜택 내역>"
        private const val NONE_MESSAGE = "없음"
        private const val TOTAL_DISCOUNT_HEADER = "<총혜택 금액>"
        private const val FINAL_PRICE_HEADER = "<할인 후 예상 결제 금액>"
        private const val BADGE_HEADER = "<12월 이벤트 배지>"

        private const val DISCOUNT_CHRISTMAS = "크리스마스 디데이 할인"
        private const val DISCOUNT_WEEKDAY = "평일 할인"
        private const val DISCOUNT_WEEKEND = "주말 할인"
        private const val DISCOUNT_SPECIAL = "특별 할인"
        private const val DISCOUNT_GIVEAWAY = "증정 이벤트"
    }
}