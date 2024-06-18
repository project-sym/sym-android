package com.ilharper.symri.entity.resource

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriGuild(
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
) : Serializable, Parcelable
