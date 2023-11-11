package christmas.domain

import christmas.InputManager
import christmas.view.OutputView

class EventPlannerProgram {
    private val inputManager = InputManager()
    private val outputView = OutputView()
    private lateinit var date: Date
    private lateinit var order: Order
    private lateinit var totalPrice: TotalPrice
    private lateinit var promotion: Promotion
    private lateinit var eventManager: EventManager


    fun run() {
        outputView.printProgramStartMessage()
        val inputDate = inputManager.getValidatedDate()
        date = Date(inputDate)
        val inputOrder = inputManager.getValidatedOrder()
        order = Order(inputOrder)
        totalPrice = order.getTotalPrice()
        promotion = Promotion(order, date, totalPrice)

        outputView.printBenefitPreview(date)
        outputView.printOrderDetails(order)
        outputView.printTotalPrice(totalPrice)

        val freeMenu = promotion.getFreeMenu()
        outputView.printFreeMenu(freeMenu)

        eventManager = EventManager(promotion)
        outputView.printDiscount(eventManager.issueDiscountReceipt())
        outputView.printTotalDiscount(eventManager.getTotalDiscount())
        outputView.printDiscountTotalPrice(eventManager.getDiscountTotalPrice(totalPrice,freeMenu))
        outputView.printBadge(eventManager.createBadge())
    }
}