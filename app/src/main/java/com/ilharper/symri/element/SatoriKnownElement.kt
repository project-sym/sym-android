package com.ilharper.symri.element

abstract class SatoriKnownElement(
    type: String,
) : SatoriElement(type) {
    lateinit var extraAttrs: MutableMap<String, String>
}
