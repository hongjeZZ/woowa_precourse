package christmas

import christmas.domain.EventPlannerProgram
import christmas.view.InputManager
import christmas.view.OutputView

fun main() {
    val eventPlannerProgram = EventPlannerProgram(InputManager(), OutputView())
    eventPlannerProgram.run()
}