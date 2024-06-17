package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriPacketIdentify(
    var body: SatoriIdentify? = null,
    override var op: Int = SatoriOp.Identify.value,
) : SatoriPacket

@JsonClass(generateAdapter = true)
class SatoriIdentify(
    var token: String? = null,
    var sequence: Int? = null,
)
