package christmas.validator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OrderValidatorTest {
    private val validator = OrderValidator()

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "타파스-2,", "레드와인-1,,타파스-5"])
    fun `주문 검증 테스트 - 공백을 입력한 경우`(invalidOrder: String) {
        assertInvalidOrderException(invalidOrder)
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스_2", "타파스", "티본스테이크-3.레드와인-2", " -2"])
    fun `주문 검증 테스트 - 잘못된 형식을 입력한 경우`(invalidOrder: String) {
        assertInvalidOrderException(invalidOrder)
    }

    @ParameterizedTest
    @ValueSource(strings = ["크리스마스 파스타-1,타파스-1", "아이스커피-5,타파스-3,시저샐러드-5"])
    fun `주문 검증 테스트 - 메뉴판에 없는 메뉴를 입력한 경우`(invalidOrder: String) {
        assertInvalidOrderException(invalidOrder)
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,제로콜라-1,타파스-3", "티본스테이크-1,아이스크림-3,바비큐립-2,아이스크림-1"])
    fun `주문 검증 테스트 - 중복된 메뉴를 입력한 경우`(invalidOrder: String) {
        assertInvalidOrderException(invalidOrder)
    }

    @ParameterizedTest
    @ValueSource(strings = ["제로콜라-1", "레드와인-2", "샴페인-5"])
    fun `주문 검증 테스트 - 음료만 입력한 경우`(invalidOrder: String) {
        assertInvalidOrderException(invalidOrder, "[ERROR] 음료만 주문할 수 없습니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["제로콜라-1,양송이수프-2,티본스테이크-0"])
    fun `주문 검증 테스트 - 메뉴의 개수가 범위를 벗어난 경우`(invalidOrder: String) {
        assertInvalidOrderException(invalidOrder)
    }

    @ParameterizedTest
    @ValueSource(strings = ["양송이수프-5,제로콜라-5,아이스크림-5,바비큐립-6"])
    fun `주문 검증 테스트 - 메뉴의 개수가 20개를 초과한 경우`(invalidOrder: String) {
        assertInvalidOrderException(invalidOrder, "[ERROR] 메뉴는 최대 20개까지 주문할 수 있습니다. 다시 입력해 주세요.")
    }

    @ParameterizedTest
    @ValueSource(
        strings = ["양송이수프-1,타파스-1,시저샐러드-1,티본스테이크-1",
            "바비큐립-1,해산물파스타-1,크리스마스파스타-1",
            "초코케이크-1,아이스크림-1,제로콜라-1,레드와인-1,샴페인-1"]
    )
    fun `주문 테스트 - 정상적인 경우`(validOrder: String) {
        validator.validate(validOrder)
    }

    private fun assertInvalidOrderException(invalidOrder: String, errorMessage: String? = null) {
        val exception = assertThrows<IllegalArgumentException> { validator.validate(invalidOrder) }
        errorMessage?.let { message ->
            assertThat(exception.message).contains(message)
        } ?: assertThat(exception.message).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
    }
}