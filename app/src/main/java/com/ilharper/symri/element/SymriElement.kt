package com.ilharper.symri.element

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser

open class SymriElement private constructor() {
    /**
     * Create [SymriElement] from jsoup [Element].
     */
    internal constructor(jsoupElement: Element) : this(
        jsoupElement.tagName(),
        jsoupElement.attributes().associate { it.key to it.value }.toMutableMap(),
        jsoupElement.children().map { SymriElement(it) }.toMutableSet(),
    )

    constructor(
        type: String,
        attrs: MutableMap<String, String>,
        children: MutableSet<SymriElement>,
    ) : this() {
        this@SymriElement.type = type
        attrsIntl = attrs
        childrenIntl = children
    }

    constructor(
        type: String,
    ) : this() {
        this@SymriElement.type = type
    }

    lateinit var type: String

    private var attrsIntl: MutableMap<String, String>? = null
    private var childrenIntl: MutableSet<SymriElement>? = null

    open var attrs: MutableMap<String, String>
        get() = attrsIntl!!
        set(value) {
            attrsIntl = value
        }

    open var children: MutableSet<SymriElement>
        get() = childrenIntl!!
        set(value) {
            childrenIntl = value
        }

    companion object {
        fun escape(
            source: String,
            inline: Boolean = false,
        ) = source
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .run {
                if (inline) {
                    replace("\"", "&quot;")
                } else {
                    this
                }
            }

        fun unescape(source: String) =
            source
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"")
                .replace(
                    Regex("""&#(\d+);"""),
                ) { matchResult ->
                    matchResult.groupValues[1].let { code ->
                        if (code == "38") matchResult.value else Char(code.toInt()).toString()
                    }
                }
                .replace(
                    Regex("""&#x([0-9a-f]+);"""),
                ) { matchResult ->
                    matchResult.groupValues[1].let { code ->
                        if (code == "26") matchResult.value else Char(code.toInt(16)).toString()
                    }
                }
                .replace(
                    Regex("""&(amp|#38|#x26);"""),
                    "&",
                )

        fun parse(source: String) = Jsoup.parse(source, Parser.xmlParser()).children().map { SymriElement(it) }
    }
}
