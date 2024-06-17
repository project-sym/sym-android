package com.ilharper.symri.entity.paging

import com.squareup.moshi.Json

enum class PagingDirection(val value: String) {
    @Json(name = "before")
    BEFORE("before"),

    @Json(name = "after")
    AFTER("after"),

    @Json(name = "around")
    AROUND("around"),
}
