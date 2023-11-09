package christmas.validator

import christmas.view.InputView

class InputManager {
    private val inputView = InputView()
    private val dateValidator = DateValidator()

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
}