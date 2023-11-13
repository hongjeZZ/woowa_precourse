package christmas.view

import christmas.domain.Badge
import christmas.domain.Date
import christmas.domain.Menu
import christmas.domain.Order
import christmas.domain.TotalPrice
import christmas.util.Formatter

class OutputView {
    fun printProgramStartMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printBenefitPreview(date: Date) {
        println("12월 ${date}에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    fun printOrderDetails(order: Order) {
        println("\n<주문 메뉴>")
        printOrder(order)
    }

    private fun printOrder(order: Order) {
        order.getOrder().entries.forEach { (menu, count) ->
            println("${Menu.getName(menu)} ${count}개")
        }
    }

    fun printTotalPrice(totalPrice: TotalPrice) {
        println("\n<할인 전 총주문 금액>")
        println(Formatter.formatPrice(totalPrice.getPrice()))
    }

    fun printGiveawayMenu(giveawayMenu: Order?) {
        println("\n<증정 메뉴>")
        giveawayMenu?.let { printOrder(it) } ?: printNone()
    }

    fun printDiscountDetails(discounts: List<Int>) {
        println("\n<혜택 내역>")
        if (discounts.sum() != 0) {
            printDiscount("크리스마스할인", discounts[0])
            printDiscount("평일 할인", discounts[1])
            printDiscount("주말 할인", discounts[2])
            printDiscount("특별 할인", discounts[3])
            printDiscount("증정 이벤트", discounts[4])
            return
        }
        printNone()
    }

    private fun printNone() {
        println("없음")
    }

    private fun printDiscount(name: String, discount: Int) {
        if (discount != 0) {
            println("$name: ${Formatter.formatDiscount(discount)}")
        }
    }

    fun printTotalDiscount(totalDiscount: Int) {
        println("\n<총혜택 금액>")
        if (totalDiscount != 0) {
            println(Formatter.formatDiscount(totalDiscount))
            return
        }
        println(Formatter.formatPrice(totalDiscount))
    }

    fun printFinalPrice(finalPrice: Int) {
        println("\n<할인 후 예상 결제 금액>")
        println(Formatter.formatPrice(finalPrice))
    }

    fun printBadge(badge: Badge) {
        println("\n<12월 이벤트 배지>")
        print(badge)
    }
}