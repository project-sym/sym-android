package com.ilharper.symri.entity.resource

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriGuildMember(
    /**
     * 用户对象
     */
    var user: SatoriUser? = null,
    /**
     * 用户在群组中的名称
     */
    var nick: String? = null,
    /**
     * 用户在群组中的头像
     */
    var avatar: String? = null,
    /**
     * 加入时间
     */
    @Json(name = "joined_at")
    var joinedAt: Long? = null,
)
