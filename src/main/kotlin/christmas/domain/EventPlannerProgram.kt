package christmas.domain

import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerProgram(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private lateinit var totalPrice: TotalPrice
    private lateinit var discountManager: DiscountManager
    private val badgeManager = BadgeManager()

    fun run() {
        initProgram()
        displayEventResults()
    }

    private fun initProgram() {
        outputView.printProgramStartMessage()
        val date = Date(inputView.getValidatedDate())
        val order = Order(inputView.getValidatedOrder())
        totalPrice = TotalPrice(order.getTotalPrice())
        discountManager = DiscountManager(order, date, totalPrice)

        outputView.run {
            printBenefitPreview(date)
            printOrderDetails(order)
            printTotalPrice(totalPrice)
        }
    }

    private fun displayEventResults() {
        val giveawayMenu = discountManager.getGiveawayMenu()
        val discounts = discountManager.getDiscounts()
        val totalDiscount = discountManager.getTotalDiscount()
        val finalPrice = discountManager.getFinalPrice(totalPrice)
        val badge = badgeManager.createBadge(totalDiscount)

        outputView.run {
            printGiveawayMenu(giveawayMenu)
            printDiscountDetails(discounts)
            printTotalDiscount(totalDiscount)
            printFinalPrice(finalPrice)
            printBadge(badge)
        }
    }
}