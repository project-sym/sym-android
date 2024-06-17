package com.ilharper.symri.entity.payload

import com.ilharper.symri.entity.paging.PagingPayload
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListChannelPayload(
    @Json(name = "guild_id")
    var guildId: String? = null,
    override var next: String? = null,
) : PagingPayload(next)
