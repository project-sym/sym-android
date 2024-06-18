package com.ilharper.symri.entity.resource

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * 登录信息
 */

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriLogin(
    /**
     * 用户对象
     */
    var user: SatoriUser? = null,
    @Json(name = "self_id")
    var selfId: String? = null,
    /**
     * 平台
     */
    var platform: String? = null,
) : Serializable, Parcelable
