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
    private val eventManager = EventManager()
    private var totalDiscount = 0

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
        totalPrice = order.getTotalPrice()
    }

    private fun displayUserDetails() {
        outputView.printBenefitPreview(date)
        outputView.printOrderDetails(order)
        outputView.printTotalPrice(totalPrice)
    }

    private fun setupEventServices() {
        eventPolicy = EventPolicy(date, totalPrice)
        eventCalculator = EventCalculator(order, date, eventPolicy)
    }

    private fun displayEventResult() {
        val freeMenu = eventCalculator.getFreeMenu()
        totalDiscount = eventCalculator.getTotalDiscount()
        outputView.printFreeMenu(freeMenu)
        printDiscounts()
        outputView.printTotalDiscount(totalDiscount)
        outputView.printDiscountTotalPrice(eventManager.getDiscountTotalPrice(totalPrice, totalDiscount, freeMenu))
        outputView.printBadge(eventManager.createBadge(totalDiscount))
    }

    private fun printDiscounts() {
        outputView.printBenefitsDetails()
        if (totalDiscount != 0) {
            outputView.printChristmasDiscount(eventCalculator.getChristmasDiscount())
            outputView.printWeekDayDiscountDiscount(eventCalculator.getWeekDayDiscount())
            outputView.printWeekendDiscount(eventCalculator.getWeekendDiscount())
            outputView.printSpecialDiscount(eventCalculator.getSpecialDiscount())
            outputView.printFreeMenuDiscount(eventCalculator.getFreeMenuDiscount())
            return
        }
        outputView.printNone()
    }
}