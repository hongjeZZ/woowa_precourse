package christmas.domain

import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerProgram(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private lateinit var totalPrice: TotalPrice
    private lateinit var eventDiscountManager: EventDiscountManager
    private val eventBadgeManager = EventBadgeManager()

    fun run() {
        initProgram()
        displayEventResults()
    }

    private fun initProgram() {
        outputView.printProgramStartMessage()
        val date = Date(inputView.getValidatedDate())
        val order = Order(inputView.getValidatedOrder())
        totalPrice = TotalPrice(order.getTotalPrice())
        eventDiscountManager = EventDiscountManager(order, date, totalPrice)

        outputView.run {
            printBenefitPreview(date)
            printOrderDetails(order)
            printTotalPrice(totalPrice)
        }
    }

    private fun displayEventResults() {
        val giveawayMenu = eventDiscountManager.getGiveawayMenu()
        val discounts = eventDiscountManager.getDiscounts()
        val totalDiscount = eventDiscountManager.getTotalDiscount()
        val finalPrice = eventDiscountManager.getFinalPrice(totalPrice)
        val badge = eventBadgeManager.createBadge(totalDiscount)

        outputView.run {
            printGiveawayMenu(giveawayMenu)
            printDiscountDetails(discounts)
            printTotalDiscount(totalDiscount)
            printFinalPrice(finalPrice)
            printBadge(badge)
        }
    }
}