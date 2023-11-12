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
        println("12월 ${date}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
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

    fun printFreeMenu(freeMenu: Order?) {
        println("\n<증정 메뉴>")
        freeMenu?.let { printOrder(it) } ?: println("없음")
    }

    fun printBenefitsDetails() {
        println("\n<혜택 내역>")
    }

    fun printChristmasDiscount(discount: Int) {
        if (discount != 0) {
            println("크리스마스할인: ${Formatter.formatDiscount(discount)}")
        }
    }

    fun printWeekDayDiscountDiscount(discount: Int) {
        if (discount != 0) {
            println("평일 할인: ${Formatter.formatDiscount(discount)}")
        }
    }

    fun printWeekendDiscount(discount: Int) {
        if (discount != 0) {
            println("주말 할인: ${Formatter.formatDiscount(discount)}")
        }
    }

    fun printSpecialDiscount(discount: Int) {
        if (discount != 0) {
            println("특별 할인: ${Formatter.formatDiscount(discount)}")
        }
    }

    fun printFreeMenuDiscount(discount: Int) {
        if (discount != 0) {
            println("증정 이벤트: ${Formatter.formatDiscount(discount)}")
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

    fun printNone() {
        println("없음")
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