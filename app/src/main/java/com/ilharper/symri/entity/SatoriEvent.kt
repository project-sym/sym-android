package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriEvent : SatoriEventBase {
    override var op: Int = SatoriOp.Event.value
}
