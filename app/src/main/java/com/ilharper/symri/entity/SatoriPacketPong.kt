package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriPacketPong : SatoriPacket {
    override var op: Int = SatoriOp.Pong.value
}
