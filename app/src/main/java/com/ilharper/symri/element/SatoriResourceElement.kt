package com.ilharper.symri.element

abstract class SatoriResourceElement(
    type: String,
) : SatoriKnownElement(type) {
    lateinit var src: String
    var cache: Boolean? = null
    var title: String? = null
    var timeout: Long? = null

    override var attrs: MutableMap<String, String>
        get() =
            extraAttrs.toMutableMap().also {
                it["src"] = src
                if (cache == true) it["cache"] = ""
                if (title != null) it["title"] = title!!
                if (timeout != null) it["timeout"] = timeout.toString()
            }
        set(value) {
            throw RuntimeException()
        }
}
