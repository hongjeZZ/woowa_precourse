package lotto.view

import lotto.domain.Lotto

class OutputView {
    fun printNumberOfLottos(lottos: List<Lotto>) {
        println("\n${lottos.size}개를 구매했습니다.")
    }
}