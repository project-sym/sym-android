package com.ilharper.symri.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriIdentify : SatoriEventBase {
    override var op: Int = SatoriOp.Identify.value

    var token: String? = null

    var sequence: Int? = null
}
