package christmas.view

import christmas.domain.Order

class OutputView {
    fun printProgramStartMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printBenefitPreview(date: Int) {
        println("12월 ${date}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    fun printOrderDetails(order: Order) {
        println("\n<주문 메뉴>")
        println(order)
    }
}