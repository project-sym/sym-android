package com.ilharper.symri.element

class SatoriImg private constructor() : SatoriResourceElement(TYPE) {
    constructor(element: SatoriElement) : this() {
        assert(element.type == TYPE)

        val extractor = AttributeExtractor(element.attrs)
        src = extractor.extractStringRequired("src")
        cache = extractor.extractBoolean("cache")
        extraAttrs = extractor.extraAttrs
    }

    constructor(
        src: String,
        cache: Boolean = false,
        title: String? = null,
        timeout: Long? = null,
    ) : this() {
        this.src = src
        this.cache = cache
        this.title = title
        this.timeout = timeout

        extraAttrs = mutableMapOf()
    }

    override var attrs: MutableMap<String, String>
        get() = super.attrs
        set(value) {
            throw RuntimeException()
        }

    companion object {
        const val TYPE = "img"
    }
}
