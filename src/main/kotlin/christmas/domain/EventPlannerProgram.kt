package christmas.domain

import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerProgram(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private lateinit var totalPrice: TotalPrice
    private lateinit var eventPlanner: EventPlanner

    fun run() {
        setProgram()
        displayEventResult()
    }

    private fun setProgram() {
        outputView.printProgramStartMessage()
        val date = Date(inputView.getValidatedDate())
        val order = Order(inputView.getValidatedOrder())
        totalPrice = TotalPrice(order.getTotalPrice())
        eventPlanner = EventPlanner(order, date, totalPrice)

        outputView.run {
            printBenefitPreview(date)
            printOrderDetails(order)
            printTotalPrice(totalPrice)
        }
    }

    private fun displayEventResult() {
        val giveawayMenu = eventPlanner.getGiveawayMenu()
        val discounts = eventPlanner.getDiscounts()
        val totalDiscounts = eventPlanner.getTotalDiscount()
        val finalPrice = eventPlanner.getFinalPrice(totalPrice)
        val badge = Badge.getBadge(totalDiscounts)

        outputView.run {
            printGiveawayMenu(giveawayMenu)
            printDiscountDetails(discounts)
            printTotalDiscount(totalDiscounts)
            printFinalPrice(finalPrice)
            printBadge(badge)
        }
    }
}