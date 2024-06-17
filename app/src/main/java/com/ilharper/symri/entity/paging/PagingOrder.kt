package com.ilharper.symri.entity.paging

import com.squareup.moshi.Json

enum class PagingOrder(val value: String) {
    @Json(name = "asc")
    ASC("asc"),

    @Json(name = "desc")
    DESC("desc"),
}
