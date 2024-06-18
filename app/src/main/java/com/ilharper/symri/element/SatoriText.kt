package com.ilharper.symri.element

import java.io.Serializable

class SatoriText private constructor() : SatoriKnownElement(TYPE), Serializable {
    constructor(element: SatoriElement) : this() {
        assert(element.type == TYPE)

        val extractor = AttributeExtractor(element.attrs)
        text = extractor.extractStringRequired("text")
        extraAttrs = extractor.extraAttrs
    }

    constructor(
        text: String,
    ) : this() {
        this.text = text

        extraAttrs = mutableMapOf()
    }

    var text: String? = null

    override var attrs: MutableMap<String, String>
        get() =
            extraAttrs.toMutableMap().also {
                it["text"] = this.text ?: ""
            }
        set(value) {
            throw RuntimeException()
        }

    companion object {
        const val TYPE = "text"
    }
}
