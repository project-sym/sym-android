package com.ilharper.symri.entity.resource

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriGuildRole(
    /**
     * 角色 ID
     */
    var id: String? = null,
    /**
     * 角色名称
     */
    var name: String? = null,
) : Serializable, Parcelable
