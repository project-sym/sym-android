package com.ilharper.symri.moshi

import com.ilharper.symri.element.SatoriElement
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class SatoriElementJsonAdapter {
    @ToJson
    fun toJson(elements: SatoriElement) = elements.toString()

    @FromJson
    fun fromJson(content: String): SatoriElement = SatoriElement.parse(content).single()
}

class SatoriElementsJsonAdapter {
    @ToJson
    fun toJson(elements: List<SatoriElement>) = elements.joinToString { it.toString() }

    @FromJson
    fun fromJson(content: String): List<SatoriElement> = SatoriElement.parse(content) // Must specify return type here
}
