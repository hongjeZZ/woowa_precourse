package christmas.domain

import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerProgram {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        outputView.printProgramStartMessage()
        inputView.readDate()
    }
}