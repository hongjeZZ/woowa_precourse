package christmas.domain

import christmas.InputManager
import christmas.view.OutputView

class EventPlannerProgram {
    private val inputManager = InputManager()
    private val outputView = OutputView()

    fun run() {
        outputView.printProgramStartMessage()
        val date = inputManager.getValidatedDate()
        val order = inputManager.getValidatedOrder()
    }
}