package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriPacketEvent(
    var body: SatoriEvent? = null,
    override var op: Int = SatoriOp.Event.value,
) : SatoriPacket
