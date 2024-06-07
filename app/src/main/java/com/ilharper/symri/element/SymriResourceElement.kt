package com.ilharper.symri.element

import kotlin.properties.Delegates

abstract class SymriResourceElement(
    type: String,
) : SymriKnownElement(type) {
    lateinit var src: String
    var cache by Delegates.notNull<Boolean>()
    var title: String? = null
    var timeout: Long? = null

    override var attrs: MutableMap<String, String>
        get() =
            extraAttrs.toMutableMap().also {
                it["src"] = src
                if (cache) it["cache"] = ""
                if (title != null) it["title"] = title!!
                if (timeout != null) it["timeout"] = timeout.toString()
            }
        set(value) {
            throw RuntimeException()
        }
}
