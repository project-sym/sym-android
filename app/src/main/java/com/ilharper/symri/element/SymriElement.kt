package com.ilharper.symri.element

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.parser.Parser
import java.util.Objects

/**
 * Base class of Satori element.
 */
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

    var children: MutableSet<SymriElement>
        get() = childrenIntl!!
        set(value) {
            childrenIntl = value
        }

    override fun toString(): String = toString(false)

    open fun toString(strip: Boolean): String {
        val attrs = attrs
        if (type == "text") {
            val content = attrs["content"]
            if (content != null) return if (strip) content else escape(content)
        }

        val inner = children.joinToString("") { it.toString(strip) }
        if (strip) return inner
        val attrsString = attrsToString(attrs)
        if (children.isEmpty()) return "<$type$attrsString />"
        return "<$type$attrsString>$inner</$type>"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other.javaClass != javaClass) return false
        val o = other as SymriElement
        return o.type == type && o.children == children && o.attrs == attrs
    }

    override fun hashCode(): Int = Objects.hash(type, children, attrs)

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

        private fun attrsToString(attrs: Map<String, String>): String =
            attrs.map {
                val key = paramCase(it.key)
                if (it.value.isEmpty()) return@map " $key"
                return@map """ $key=${escape(it.value, true)}"""
            }.joinToString("")

        private fun uncapitalize(source: String) = source[0].lowercase() + source.substring(1)

        private fun paramCase(source: String) =
            uncapitalize(source)
                .replace('_', '-')
                .replace(
                    Regex(""".[A-Z]+"""),
                ) { it.value[0] + "-" + it.value.substring(1).lowercase() }
    }
}
