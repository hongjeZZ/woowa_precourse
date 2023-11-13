package christmas.validator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MenuValidatorTest {
    private val validator = MenuValidator()

    @Test
    fun `메뉴판에 없는 메뉴를 입력한 경우`() {
        val invalidMenus = listOf("케이크", "스테이크", "레드 와인")

        assertThrows<IllegalArgumentException> { validator.validate(invalidMenus) }.also { exception ->
            assertThat(exception.message).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `중복된 메뉴를 입력한 경우`() {
        val invalidMenus = listOf("타파스", "레드와인", "타파스")

        assertThrows<IllegalArgumentException> { validator.validate(invalidMenus) }.also { exception ->
            assertThat(exception.message).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `음료만 주문했을 경우`() {
        val invalidMenus = listOf("레드와인", "제로콜라", "샴페인")

        assertThrows<IllegalArgumentException> { validator.validate(invalidMenus) }.also { exception ->
            assertThat(exception.message).contains("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.")
        }
    }

    @Test
    fun `정상적인 주문 테스트`() {
        val validMenus = listOf(
            "양송이수프",
            "타파스",
            "시저샐러드",
            "티본스테이크",
            "바비큐립",
            "해산물파스타",
            "크리스마스파스타",
            "초코케이크",
            "아이스크림",
            "제로콜라",
            "레드와인",
            "샴페인"
        )
        validator.validate(validMenus)
    }
}