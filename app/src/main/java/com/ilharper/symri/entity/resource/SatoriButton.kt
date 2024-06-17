package com.ilharper.symri.entity.resource

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriButton(
    /**
     * 按钮 ID
     */
    var id: String? = null,
)
