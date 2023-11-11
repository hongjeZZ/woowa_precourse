package christmas.domain

import christmas.view.InputManager
import christmas.view.OutputView

class EventPlannerProgram(
    private val inputManager: InputManager,
    private val outputView: OutputView,
) {
    private lateinit var date: Date
    private lateinit var order: Order
    private lateinit var totalPrice: TotalPrice
    private lateinit var eventPolicy: EventPolicy
    private lateinit var eventCalculator: EventCalculator
    private lateinit var eventManager: EventManager


    fun run() {
        init()
        process()
        displayEventResult()
    }

    private fun init() {
        outputView.printProgramStartMessage()
        date = Date(inputManager.getValidatedDate())
        order = Order(inputManager.getValidatedOrder())
        totalPrice = order.getTotalPrice()
        printUserDetails()
    }

    private fun process() {
        eventPolicy = EventPolicy(date, totalPrice)
        eventCalculator = EventCalculator(order, date, eventPolicy)
        eventManager = EventManager(eventCalculator)
    }

    private fun printUserDetails() {
        outputView.run {
            printBenefitPreview(date)
            printOrderDetails(order)
            printTotalPrice(totalPrice)
        }
    }

    private fun displayEventResult() {
        val freeMenu = eventCalculator.getFreeMenu()
        outputView.run {
            printFreeMenu(freeMenu)
            printDiscount(eventManager.issueDiscountReceipt())
            printTotalDiscount(eventManager.getTotalDiscount())
            printDiscountTotalPrice(eventManager.getDiscountTotalPrice(totalPrice, freeMenu))
            printBadge(eventManager.createBadge())
        }
    }
}