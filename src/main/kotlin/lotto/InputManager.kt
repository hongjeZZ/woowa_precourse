package lotto

import lotto.validator.BonusNumberValidator
import lotto.validator.InputMoneyValidator
import lotto.validator.WinningNumbersValidator
import lotto.view.InputView

class InputManager {
    private val inputView = InputView()
    private val inputMoneyValidator = InputMoneyValidator()
    private val winningNumbersValidator = WinningNumbersValidator()
    private val bonusNumberValidator = BonusNumberValidator()

    fun getInputMoney(): Int {
        return try {
            val inputMoney = inputView.readInputMoneyFromUser()
            inputMoneyValidator.validate(inputMoney)
            inputMoney.toInt()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getInputMoney()
        }
    }

    fun getWinningNumbers(): List<Int> {
        return try {
            val winningNumber = inputView.readWinningNumbersFromUser()
            winningNumbersValidator.validate(winningNumber)
            winningNumber.split(",").map { it.trim().toInt() }
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getWinningNumbers()
        }
    }

    fun getBonusNumber(winningNumber: List<Int>): Int {
        return try {
            val bonusNumber = inputView.readBonusNumberFromUser()
            bonusNumberValidator.validate(bonusNumber, winningNumber)
            bonusNumber.toInt()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getBonusNumber(winningNumber)
        }
    }
}