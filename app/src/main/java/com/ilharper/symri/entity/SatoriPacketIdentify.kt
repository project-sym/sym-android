package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriPacketIdentify : SatoriPacket {
    override var op: Int = SatoriOp.Identify.value

    var token: String? = null

    var sequence: Int? = null
}
