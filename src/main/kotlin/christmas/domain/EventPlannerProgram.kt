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
    private lateinit var eventPolicy: EventPolicy
    private lateinit var eventCalculator: EventCalculator

    fun run() {
        getUserDetails()
        displayUserDetails()
        setupEventServices()
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

    private fun setupEventServices() {
        eventPolicy = EventPolicy(date, totalPrice)
        eventCalculator = EventCalculator(eventPolicy)
    }

    private fun displayEventResult() {
        val totalDiscount = eventCalculator.getTotalDiscount(order,date)
        outputView.printFreeMenu(eventCalculator.getFreeMenu())
        printDiscounts(totalDiscount)
        outputView.printTotalDiscount(totalDiscount)
        outputView.printDiscountTotalPrice(eventCalculator.getDiscountTotalPrice(totalPrice))
        outputView.printBadge(eventCalculator.createBadge())
    }

    private fun printDiscounts(totalDiscount: Int) {
        outputView.printBenefitsDetails()
        if (totalDiscount != 0) {
            outputView.printChristmasDiscount(eventCalculator.getChristmasDiscount(date))
            outputView.printWeekDayDiscountDiscount(eventCalculator.getWeekDayDiscount(order))
            outputView.printWeekendDiscount(eventCalculator.getWeekendDiscount(order))
            outputView.printSpecialDiscount(eventCalculator.getSpecialDiscount())
            outputView.printFreeMenuDiscount(eventCalculator.getFreeMenuDiscount())
            return
        }
        outputView.printNone()
    }
}