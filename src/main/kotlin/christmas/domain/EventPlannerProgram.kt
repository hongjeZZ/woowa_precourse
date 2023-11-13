package christmas.domain

import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerProgram(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private lateinit var date: Date
    private lateinit var order: Order
    private lateinit var totalPrice: TotalPrice
    private lateinit var eventPlanner: EventPlanner

    fun run() {
        getUserDetails()
        displayUserDetails()
        displayEventResult()
    }

    private fun getUserDetails() {
        outputView.printProgramStartMessage()
        date = Date(inputView.getValidatedDate())
        order = Order(inputView.getValidatedOrder())
        totalPrice = TotalPrice(order.getTotalPrice())
    }

    private fun displayUserDetails() {
        outputView.run {
            printBenefitPreview(date)
            printOrderDetails(order)
            printTotalPrice(totalPrice)
        }
    }

    private fun displayEventResult() {
        eventPlanner = EventPlanner(order, date, totalPrice)

        outputView.run {
            printGiveawayMenu(eventPlanner.getGiveawayMenu())
            printDiscountDetails(eventPlanner.getDiscounts())
            printTotalDiscount(eventPlanner.getTotalDiscount())
            printFinalPrice(eventPlanner.getFinalPrice(totalPrice))
            printBadge(eventPlanner.createBadge())
        }
    }
}