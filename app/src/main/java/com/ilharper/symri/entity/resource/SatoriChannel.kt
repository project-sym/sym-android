package com.ilharper.symri.entity.resource

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
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
) : Serializable, Parcelable
