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
    private lateinit var eventManager: EventManager

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
        outputView.printBenefitPreview(date)
        outputView.printOrderDetails(order)
        outputView.printTotalPrice(totalPrice)
    }

    private fun displayEventResult() {
        eventManager = EventManager(order, date, totalPrice)
        outputView.printGiveawayMenu(eventManager.getGiveawayMenu())
        outputView.printDiscountDetails(eventManager.getDiscounts())
        outputView.printTotalDiscount(eventManager.getTotalDiscount())
        outputView.printFinalPrice(eventManager.getFinalPrice(totalPrice))
        outputView.printBadge(eventManager.createBadge())
    }
}