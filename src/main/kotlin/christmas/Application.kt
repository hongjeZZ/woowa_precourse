package christmas

import christmas.domain.EventPlannerProgram
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val eventPlannerProgram = EventPlannerProgram(InputView(), OutputView())
    eventPlannerProgram.run()
}