package com.ilharper.symri.entity.resource

import com.squareup.moshi.Json

/**
 * 登录信息
 */
class SatoriLogin {
    /**
     * 用户对象
     */
    var user: SatoriUser? = null

    @Json(name = "self_id")
    var selfId: String? = null

    /**
     * 平台
     */
    var platform: String? = null
}
