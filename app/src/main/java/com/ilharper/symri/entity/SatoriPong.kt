package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriPong : SatoriEventBase {
    override var op: Int = SatoriOp.Pong.value
}
