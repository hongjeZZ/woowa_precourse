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
    private lateinit var eventManager: EventManager


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
        eventManager = EventManager(eventCalculator)
    }

    private fun displayEventResult() {
        val freeMenu = eventCalculator.getFreeMenu()
        outputView.printFreeMenu(freeMenu)
        outputView.printDiscountReceipt(eventManager.issueDiscountReceipt())
        outputView.printTotalDiscount(eventManager.getTotalDiscount())
        outputView.printDiscountTotalPrice(eventManager.getDiscountTotalPrice(totalPrice, freeMenu))
        outputView.printBadge(eventManager.createBadge())
    }
}