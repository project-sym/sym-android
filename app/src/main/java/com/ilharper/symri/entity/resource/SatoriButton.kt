package com.ilharper.symri.entity.resource

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriButton(
    /**
     * 按钮 ID
     */
    var id: String? = null,
) : Serializable, Parcelable
