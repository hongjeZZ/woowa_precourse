package christmas.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SplitterTest {
    @Test
    fun `쉼표로 문자열을 분리하는 테스트`() {
        val result = Splitter.splitByComma("apple,orange,banana")
        assertThat(result).containsExactly("apple", "orange", "banana")
    }

    @Test
    fun `쉼표로 문자열을 분리하는 테스트 - 공백을 입력할 경우`() {
        val result = Splitter.splitByComma(",,")
        assertThat(result).containsExactly("", "", "")
    }

    @Test
    fun `쉼표로 문자열을 분리하는 테스트 - 쉼표가 없는 경우`() {
        val result = Splitter.splitByComma("apple")
        assertThat(result).containsExactly("apple")
    }

    @Test
    fun `하이픈으로 문자열을 분리하는 테스트`() {
        val result = Splitter.splitByHyphen("apple-orange-banana")
        assertThat(result).containsExactly("apple", "orange", "banana")
    }

    @Test
    fun `하이픈으로 문자열을 분리하는 테스트 - 공백을 입력할 경우`() {
        val result = Splitter.splitByHyphen("--")
        assertThat(result).containsExactly("", "", "")
    }

    @Test
    fun `하이픈으로 문자열을 분리하는 테스트 - 하이픈이 없는 경우`() {
        val result = Splitter.splitByHyphen("apple")
        assertThat(result).containsExactly("apple")
    }
}