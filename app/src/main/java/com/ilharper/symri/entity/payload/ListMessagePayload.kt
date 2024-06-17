package com.ilharper.symri.entity.payload

import com.ilharper.symri.entity.paging.PagingDirection
import com.ilharper.symri.entity.paging.PagingOrder
import com.ilharper.symri.entity.paging.PagingPayload
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListMessagePayload(
    @Json(name = "channel_id")
    var channelId: String? = null,
    override var next: String? = null,
    override var direction: PagingDirection? = null,
    override var limit: Int? = null,
    override var order: PagingOrder? = null,
) : PagingPayload(next, direction, limit, order)
