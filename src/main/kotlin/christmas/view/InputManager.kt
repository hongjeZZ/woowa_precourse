package christmas.view

import christmas.validator.DateValidator
import christmas.validator.OrderValidator

class InputManager {
    private val inputView = InputView()
    private val dateValidator = DateValidator()
    private val orderValidator = OrderValidator()

    fun getValidatedDate(): Int {
        return try {
            val inputDate = inputView.readDate()
            dateValidator.validate(inputDate)
            inputDate.toInt()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidatedDate()
        }
    }

    fun getValidatedOrder(): String {
        return try {
            val order = inputView.readMenu()
            orderValidator.validate(order)
            order
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidatedOrder()
        }
    }
}