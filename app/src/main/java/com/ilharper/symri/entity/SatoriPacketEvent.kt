package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriPacketEvent : SatoriPacket {
    override var op: Int = SatoriOp.Event.value
}
