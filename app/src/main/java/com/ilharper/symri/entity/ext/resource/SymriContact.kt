package com.ilharper.symri.entity.ext.resource

import android.os.Parcelable
import com.ilharper.symri.entity.resource.SatoriChannelType
import com.ilharper.symri.entity.resource.SatoriLogin
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * 会话
 */

@Parcelize
@JsonClass(generateAdapter = true)
class SymriContact(
    var logins: List<SatoriLogin>? = null,
    /**
     * 群组 ID
     */
    var id: String? = null,
    /**
     * 群组名称
     */
    var name: String? = null,
    /**
     * 群组头像
     */
    var avatar: String? = null,
    /**
     * 平台
     */
    var platform: String? = null,
    /**
     * 会话类型
     */
    var type: SatoriChannelType? = null,
) : Serializable, Parcelable
