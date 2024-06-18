package com.ilharper.symri.entity.resource

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
@JsonClass(generateAdapter = true)
class SatoriArgv(
    /**
     * 指令名称
     */
    var name: String? = null,
    // /**
    //  * 参数
    //  */
    // var arguments:	List<Any>? = null,
    // /**
    //  * 选项
    //  */
    // var options: Any? = null,
) : Serializable, Parcelable
