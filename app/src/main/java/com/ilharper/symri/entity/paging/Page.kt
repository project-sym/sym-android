package com.ilharper.symri.entity.paging

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Page<T>(
    var data: List<T>? = null,
    var prev: String? = null,
    var next: String? = null,
)
