package com.ilharper.symri.element

import android.os.Parcel
import android.os.Parcelable
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode
import org.jsoup.parser.Parser
import java.util.Objects

/**
 * Base class of Satori element.
 */
open class SatoriElement private constructor() : Parcelable {
    /**
     * Create [SatoriElement] from jsoup [Element].
     */
    internal constructor(jsoupElement: Element) : this(
        jsoupElement.tagName(),
        jsoupElement.attributes().associate { it.key to it.value }.toMutableMap(),
        jsoupElement.children().map { SatoriElement(it) }.toMutableList(),
    )

    /**
     * Create [SatoriElement] (text element) from jsoup [TextNode].
     */
    internal constructor(jsoupTextNode: TextNode) : this(
        "text",
        mutableMapOf(
            "content" to jsoupTextNode.text(),
        ),
        mutableListOf(),
    )

    constructor(
        type: String,
        attrs: MutableMap<String, String>,
        children: MutableList<SatoriElement>,
    ) : this() {
        this.type = type
        attrsIntl = attrs
        this.children = children
    }

    constructor(
        type: String,
    ) : this() {
        this.type = type
        children = mutableListOf()
    }

    constructor(parcel: Parcel) : this() {
        when (val jsoupNode = Jsoup.parse(parcel.readString()!!, Parser.xmlParser()).childNode(0)) {
            is Element -> {
                type = jsoupNode.tagName()
                attrsIntl = jsoupNode.attributes().associate { it.key to it.value }.toMutableMap()
                children = jsoupNode.children().map { SatoriElement(it) }.toMutableList()
            }

            is TextNode -> {
                type = "text"
                attrsIntl = mutableMapOf()
                children = mutableListOf()
            }

            else -> throw RuntimeException()
        }
    }

    lateinit var type: String
    lateinit var children: MutableList<SatoriElement>

    private var attrsIntl: MutableMap<String, String>? = null

    open var attrs: MutableMap<String, String>
        get() = attrsIntl!!
        set(value) {
            attrsIntl = value
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
        if (other == null) return false
        if (other.javaClass != javaClass) return false
        val o = other as SatoriElement
        return o.type == type && o.children == children && o.attrs == attrs
    }

    override fun hashCode(): Int = Objects.hash(type, children, attrs)

    override fun writeToParcel(
        parcel: Parcel,
        flags: Int,
    ) = parcel.writeString(toString())

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SatoriElement> {
        override fun createFromParcel(parcel: Parcel): SatoriElement {
            return SatoriElement(parcel)
        }

        override fun newArray(size: Int): Array<SatoriElement?> {
            return arrayOfNulls(size)
        }

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

        fun parse(
            source: String,
            ignoreKnownElement: Boolean = false,
        ) = Jsoup.parse(source, Parser.xmlParser()).childNodes().map {
            if (ignoreKnownElement) {
                when (it) {
                    is Element -> SatoriElement(it)

                    is TextNode ->
                        SatoriElement(
                            "text",
                            mutableMapOf(
                                "content" to it.text(),
                            ),
                            mutableListOf(),
                        )

                    else -> throw RuntimeException()
                }
            } else {
                when (it) {
                    is Element ->
                        when (it.tagName()) {
                            "img" -> SatoriImg(SatoriElement(it))
                            else -> SatoriElement(it)
                        }

                    is TextNode -> SatoriText(it.text())

                    else -> throw RuntimeException()
                }
            }
        }
            .toMutableList()

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
