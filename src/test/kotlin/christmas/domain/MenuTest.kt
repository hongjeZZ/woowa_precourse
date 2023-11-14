package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MenuTest {
    @ParameterizedTest
    @CsvSource(
        "양송이수프, MUSHROOM_SOUP",
        "타파스, TAPAS",
        "시저샐러드, CAESAR_SALAD",
        "티본스테이크, T_BONE_STEAK",
        "바비큐립, BBQ_RIBS",
        "해산물파스타, SEAFOOD_PASTA",
        "크리스마스파스타, CHRISTMAS_PASTA",
        "초코케이크, CHOCO_CAKE",
        "아이스크림, ICE_CREAM",
        "제로콜라, ZERO_COLA",
        "레드와인, RED_WINE",
        "샴페인, CHAMPAGNE"
    )
    fun `메뉴 이름을 통해 메뉴를 생성하는 기능 테스트`(menuName: String, expectedMenu: Menu) {
        // When
        val menu = Menu.getMenu(menuName)

        // Then
        assertThat(menu).isEqualTo(expectedMenu)
    }

    @ParameterizedTest
    @CsvSource(
        "MUSHROOM_SOUP, 에피타이저",
        "TAPAS, 에피타이저",
        "CAESAR_SALAD, 에피타이저",
        "T_BONE_STEAK, 메인",
        "BBQ_RIBS, 메인",
        "SEAFOOD_PASTA, 메인",
        "CHRISTMAS_PASTA, 메인",
        "CHOCO_CAKE, 디저트",
        "ICE_CREAM, 디저트",
        "ZERO_COLA, 음료",
        "RED_WINE, 음료",
        "CHAMPAGNE, 음료"
    )
    fun `메뉴를 통해 타입을 가져오는 기능 테스트`(menu: Menu, expectedType: String) {
        // When
        val typeName = Menu.getType(menu)

        // Then
        assertThat(typeName).isEqualTo(expectedType)
    }

    @ParameterizedTest
    @CsvSource(
        "MUSHROOM_SOUP, 6_000",
        "TAPAS, 5_500",
        "CAESAR_SALAD, 8_000",
        "T_BONE_STEAK, 55_000",
        "BBQ_RIBS, 54_000",
        "SEAFOOD_PASTA, 35_000",
        "CHRISTMAS_PASTA, 25_000",
        "CHOCO_CAKE, 15_000",
        "ICE_CREAM, 5_000",
        "ZERO_COLA, 3_000",
        "RED_WINE, 60_000",
        "CHAMPAGNE, 25_000"
    )
    fun `메뉴를 통해 가격을 가져오는 기능 테스트`(menu: Menu, expectedPrice: Int) {
        // When
        val price = Menu.getPrice(menu)

        // Then
        assertThat(price).isEqualTo(expectedPrice)
    }

    @ParameterizedTest
    @CsvSource(
        "MUSHROOM_SOUP, 양송이수프",
        "TAPAS, 타파스",
        "CAESAR_SALAD, 시저샐러드",
        "T_BONE_STEAK, 티본스테이크",
        "BBQ_RIBS, 바비큐립",
        "SEAFOOD_PASTA, 해산물파스타",
        "CHRISTMAS_PASTA, 크리스마스파스타",
        "CHOCO_CAKE, 초코케이크",
        "ICE_CREAM, 아이스크림",
        "ZERO_COLA, 제로콜라",
        "RED_WINE, 레드와인",
        "CHAMPAGNE, 샴페인"
    )
    fun `메뉴를 통해 이름을 가져오는 기능 테스트`(menu: Menu, expectedName: String) {
        // When
        val name = Menu.getName(menu)

        // Then
        assertThat(name).isEqualTo(expectedName)
    }

    @ParameterizedTest
    @CsvSource(
        "제로콜라, true", "티본스테이크, false", "해산물파스타, false"
    )
    fun `특정 이름의 메뉴가 음료인지 판별하는 기능 테스트`(menuName: String, expectedIsBeverage: Boolean) {
        // When
        val result = Menu.isBeverage(menuName)

        // Then
        assertThat(result).isEqualTo(expectedIsBeverage)
    }

    @ParameterizedTest
    @CsvSource(
        "양송이수프, true", "시저샐러드, true", "코카콜라, false", "생크림케이크, false"
    )
    fun `특정 이름의 메뉴가 메뉴판에 있는 메뉴인지 판별하는 기능 테스트`(menuName: String, hasMenu: Boolean) {
        // When
        val result = Menu.hasMenu(menuName)

        // Then
        assertThat(result).isEqualTo(hasMenu)
    }
}