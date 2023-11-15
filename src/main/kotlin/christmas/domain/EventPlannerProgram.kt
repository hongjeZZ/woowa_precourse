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
    private lateinit var badgeManager: BadgeManager
    private var totalDiscount = 0

    fun run() {
        printProgramStartMessage()
        initProgram()
        printUserDetails()
        setEventManager()
        displayEventResults()
    }

    private fun printProgramStartMessage() {
        outputView.printProgramStartMessage()
    }

    private fun initProgram() {
        date = Date(inputView.getValidatedDate())
        order = Order(inputView.getValidatedOrder())
        totalPrice = TotalPrice(order.getTotalPrice())
    }

    private fun printUserDetails() {
        outputView.run {
            printBenefitPreview(date)
            printOrderDetails(order)
            printTotalPrice(totalPrice)
        }
    }

    private fun setEventManager() {
        eventManager = EventManager(order, date, totalPrice)
        badgeManager = BadgeManager()
    }

    private fun displayEventResults() {
        displayGiveawayMenu()
        displayDiscountDetails()
        displayTotalDiscount()
        displayFinalPrice()
        displayBadge()
    }

    private fun displayGiveawayMenu() {
        val giveawayMenu = eventManager.getGiveawayMenu()
        outputView.printGiveawayMenu(giveawayMenu)
    }

    private fun displayDiscountDetails() {
        val discounts = eventManager.getDiscounts()
        outputView.printDiscountDetails(discounts)
    }

    private fun displayTotalDiscount() {
        totalDiscount = eventManager.getTotalDiscount()
        outputView.printTotalDiscount(totalDiscount)
    }

    private fun displayFinalPrice() {
        val finalPrice = eventManager.getFinalPrice(totalPrice)
        outputView.printFinalPrice(finalPrice)
    }

    private fun displayBadge() {
        val badge = badgeManager.createBadge(totalDiscount)
        outputView.printBadge(badge)
    }
}