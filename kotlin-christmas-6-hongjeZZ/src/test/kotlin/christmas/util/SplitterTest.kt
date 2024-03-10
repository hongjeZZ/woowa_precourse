package christmas.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SplitterTest {
    @Test
    fun `쉼표로 문자열을 분리하는 테스트`() {
        // Given
        val inputString = "apple,orange,banana"

        // When
        val result = Splitter.splitByComma(inputString)

        // Then
        assertThat(result).containsExactly("apple", "orange", "banana")
    }

    @Test
    fun `쉼표로 문자열을 분리하는 테스트 - 공백을 입력할 경우`() {
        // Given
        val inputString = ",,"

        // When
        val result = Splitter.splitByComma(inputString)

        // Then
        assertThat(result).containsExactly("", "", "")
    }

    @Test
    fun `쉼표로 문자열을 분리하는 테스트 - 쉼표가 없는 경우`() {
        // Given
        val inputString = "apple"

        // When
        val result = Splitter.splitByComma(inputString)

        // Then
        assertThat(result).containsExactly("apple")
    }

    @Test
    fun `하이픈으로 문자열을 분리하는 테스트`() {
        // Given
        val inputString = "apple-orange-banana"

        // When
        val result = Splitter.splitByHyphen(inputString)

        // Then
        assertThat(result).containsExactly("apple", "orange", "banana")
    }

    @Test
    fun `하이픈으로 문자열을 분리하는 테스트 - 공백을 입력할 경우`() {
        // Given
        val inputString = "--"

        // When
        val result = Splitter.splitByHyphen(inputString)

        // Then
        assertThat(result).containsExactly("", "", "")
    }

    @Test
    fun `하이픈으로 문자열을 분리하는 테스트 - 하이픈이 없는 경우`() {
        // Given
        val inputString = "apple"

        // When
        val result = Splitter.splitByHyphen(inputString)

        // Then
        assertThat(result).containsExactly("apple")
    }
}