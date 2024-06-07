package com.ilharper.symri.element

import com.ilharper.symri.element.SymriElement.Companion.escape
import com.ilharper.symri.element.SymriElement.Companion.parse
import com.ilharper.symri.element.SymriElement.Companion.unescape
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class SymriElementTest {
    @Test
    fun escapeElements() {
        assertEquals("&lt;foo&gt;", escape("<foo>"))
        assertEquals("&amp;quot;", escape("&quot;"))
    }

    @Test
    fun unescapeElements() {
        assertEquals("<foo>", unescape("&lt;foo&gt;"))
        assertEquals("&quot;", unescape("&amp;quot;"))
        assertEquals("&#39;", unescape("&#38;#39;"))
    }

    @Test
    fun parseBasic() {
        assertArrayEquals(
            mutableSetOf(
                SymriImg("https://test.com/?foo=1&bar=2"),
            ).toTypedArray(),
            parse("""<img src="https://test.com/?foo=1&amp;bar=2"/>""").toTypedArray(),
        )
        TODO()
    }

    @Test
    fun parseMismatchedTags() {
        TODO()
    }

    @Test
    fun parseWhitespace() {
        TODO()
    }

    @Test
    fun stringBasic() {
        TODO()
    }

    @Test
    fun stringValidateChildren() {
        TODO()
    }

    @Test
    fun selectType() {
        TODO()
    }

    @Test
    fun selectDescendant() {
        TODO()
    }

    @Test
    fun selectSibling() {
        TODO()
    }
}
