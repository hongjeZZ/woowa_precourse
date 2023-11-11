package christmas.view

import christmas.domain.Badge
import christmas.domain.Date
import christmas.domain.Order
import christmas.domain.TotalPrice
import christmas.util.Formatter

class OutputView {
    fun printProgramStartMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printBenefitPreview(date: Date) {
        println("12월 ${date}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    fun printOrderDetails(order: Order) {
        println("\n<주문 메뉴>")
        println(order)
    }

    fun printTotalPrice(totalPrice: TotalPrice) {
        println("\n<할인 전 총주문 금액>")
        println(totalPrice)
    }

    fun printFreeMenu(freeMenu: Order?) {
        println("\n<증정 메뉴>")
        println(freeMenu ?: "없음")
    }

    fun printDiscount(discountReceipt: String) {
        println("\n<혜택 내역>")
        println(discountReceipt)
    }

    fun printTotalDiscount(totalDiscount: Int) {
        println("\n<총혜택 금액>")
        println(Formatter.formatDiscount(totalDiscount))
    }

    fun printDiscountTotalPrice(discountTotalPrice: Int) {
        println("\n<할인 후 예상 결제 금액>")
        println(Formatter.formatPrice(discountTotalPrice))
    }

    fun printBadge(badge: Badge) {
        println("\n<12월 이벤트 배지>")
        print(badge)
    }
}