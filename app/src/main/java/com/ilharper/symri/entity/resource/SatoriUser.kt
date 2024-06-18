package com.ilharper.symri.entity.resource

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriUser(
    /**
     * 用户 ID
     */
    var id: String? = null,
    /**
     * 用户名称
     */
    var name: String? = null,
    /**
     * 用户昵称
     */
    var nick: String? = null,
    /**
     * 用户头像
     */
    var avatar: String? = null,
    @Json(name = "is_bot")
    var isBot: Boolean = false,
) : Serializable, Parcelable
