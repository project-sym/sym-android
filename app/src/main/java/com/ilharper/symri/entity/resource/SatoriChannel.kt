package com.ilharper.symri.entity.resource

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriChannel(
    /**
     * 频道 ID
     */
    var id: String? = null,
    /**
     * 频道类型
     */
    var type: SatoriChannelType? = null,
    /**
     * 频道名称
     */
    var name: String? = null,
    /**
     * 父频道 ID
     */
    @Json(name = "parent_id")
    var parentId: String? = null,
)
