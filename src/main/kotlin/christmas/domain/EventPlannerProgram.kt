package christmas.domain

import christmas.InputManager
import christmas.view.OutputView

class EventPlannerProgram {
    private val inputManager = InputManager()
    private val outputView = OutputView()
    private val promotion = Promotion()
    private lateinit var totalPrice: TotalPrice
    private lateinit var order: Order
    private lateinit var date: Date

    fun run() {
        outputView.printProgramStartMessage()
        val inputDate = inputManager.getValidatedDate()
        date = Date(inputDate)
        val inputOrder = inputManager.getValidatedOrder()
        order = Order(inputOrder)
        totalPrice = order.getTotalPrice()

        outputView.printBenefitPreview(date)
        outputView.printOrderDetails(order)
        outputView.printTotalPrice(totalPrice)

        val freeMenu = promotion.getFreeMenu(totalPrice)
        outputView.printFreeMenu(freeMenu)
    }
}