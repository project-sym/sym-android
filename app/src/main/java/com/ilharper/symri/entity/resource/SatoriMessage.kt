package com.ilharper.symri.entity.resource

import android.os.Parcelable
import com.ilharper.symri.element.SatoriElement
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriMessage(
    /**
     * 消息 ID
     */
    var id: String? = null,
    /**
     * 消息内容
     */
    var content: List<SatoriElement>? = null,
    /**
     * 频道对象
     */
    var channel: SatoriChannel? = null,
    /**
     * 群组对象
     */
    var guild: SatoriGuild? = null,
    /**
     * 群组成员对象
     */
    var member: SatoriGuildMember? = null,
    /**
     * 用户对象
     */
    var user: SatoriUser? = null,
    /**
     * 消息发送的时间戳
     */
    @Json(name = "created_at")
    var createdAt: Long? = null,
    /**
     * 消息修改的时间戳
     */
    @Json(name = "updated_at")
    var updatedAt: Long? = null,
    /**
     * 消息发送的时间戳
     */
    @Deprecated("use createdAt instead")
    var timestamp: Long? = null,
) : Serializable, Parcelable
