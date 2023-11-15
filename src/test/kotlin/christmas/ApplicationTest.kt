package christmas

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class ApplicationTest : NsTest() {
    @Test
    fun `모든 타이틀 출력`() {
        assertSimpleTest {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1")
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            )
        }
    }

    @Test
    fun `혜택 내역 없음 출력`() {
        assertSimpleTest {
            run("26", "타파스-1,제로콜라-1")
            assertThat(output()).contains("<혜택 내역>$LINE_SEPARATOR".toString() + "없음")
        }
    }

    @Test
    fun `날짜 예외 테스트`() {
        assertSimpleTest {
            runException("a")
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `날짜 예외 테스트 - 날짜 범위보다 작은 값을 입력한 경우`() {
        assertSimpleTest {
            runException("0")
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `날짜 예외 테스트 - 날짜 범위보다 큰 값을 입력한 경우`() {
        assertSimpleTest {
            runException("32")
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `주문 예외 테스트`() {
        assertSimpleTest {
            runException("3", "제로콜라-a")
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `주문 예외 테스트 - 중복된 주문을 입력한 경우`() {
        assertSimpleTest {
            runException("3", "타파스-1,제로콜라-1,타파스-1")
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `주문 예외 테스트 - 메뉴판에 없는 메뉴를 입력한 경우`() {
        assertSimpleTest {
            runException("3", "초코우유-1")
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `주문 예외 테스트 - 음료만 주문하는 경우`() {
        assertSimpleTest {
            runException("3", "제로콜라-1,레드와인-1")
            assertThat(output()).contains("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `주문 예외 테스트 - 메뉴의 개수가 20개를 초과할 경우`() {
        assertSimpleTest {
            runException("3", "티본스테이크-5,레드와인-5,아이스크림-5,초코케이크-6")
            assertThat(output()).contains("[ERROR] 메뉴는 최대 20개까지 주문할 수 있습니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `증정 메뉴 없음 출력`() {
        assertSimpleTest {
            run("26", "타파스-1,제로콜라-1")
            assertThat(output()).contains("<증정 메뉴>$LINE_SEPARATOR".toString() + "없음")
        }
    }

    @Test
    fun `총혜택 금액 출력`() {
        assertSimpleTest {
            run("26", "타파스-1,제로콜라-1")
            assertThat(output()).contains("<총혜택 금액>$LINE_SEPARATOR".toString() + "0원")
        }
    }

    override fun runMain() {
        main()
    }

    companion object {
        private val LINE_SEPARATOR = System.lineSeparator()
    }
}
