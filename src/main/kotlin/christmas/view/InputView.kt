package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.validator.DateValidator
import christmas.validator.OrderValidator

class InputView {
    private val dateValidator = DateValidator()
    private val orderValidator = OrderValidator()

    fun getValidatedDate(): Int {
        return try {
            val inputDate = readDate()
            dateValidator.validate(inputDate)
            inputDate.toInt()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidatedDate()
        }
    }

    fun getValidatedOrder(): String {
        return try {
            val order = readOrder()
            orderValidator.validate(order)
            order
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidatedOrder()
        }
    }

    private fun readDate(): String {
        println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)")
        return Console.readLine()
    }

    private fun readOrder(): String {
        println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)")
        return Console.readLine().trim()
    }
}