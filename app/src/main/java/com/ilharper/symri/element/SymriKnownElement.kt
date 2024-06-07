package com.ilharper.symri.element

abstract class SymriKnownElement(
    type: String,
) : SymriElement(type) {
    lateinit var extraAttrs: MutableMap<String, String>
}
