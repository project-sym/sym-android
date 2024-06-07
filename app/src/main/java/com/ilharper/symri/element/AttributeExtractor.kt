package com.ilharper.symri.element

internal class AttributeExtractor(
    attrs: MutableMap<String, String>,
) {
    val extraAttrs: MutableMap<String, String> = attrs.toMutableMap()

    fun extractStringRequired(key: String): String {
        val value = extraAttrs[key]
        assert(value != null)
        extraAttrs.remove(key)
        return value!!
    }

    fun extractBoolean(key: String): Boolean {
        val t = extraAttrs[key] != null
        val f = extraAttrs["no-$key"] != null
        assert(!(t && f))
        extraAttrs.remove(key)
        extraAttrs.remove("no-$key")
        return if (f) {
            false
        } else if (t) {
            true
        } else {
            false
        }
    }
}
