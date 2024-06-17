package com.ilharper.symri.entity.paging

open class PagingPayload(
    /**
     * 分页令牌
     */
    open var next: String? = null,
    /**
     * 查询方向
     */
    open var direction: PagingDirection? = null,
    /**
     * 消息数量限制
     */
    open var limit: Int? = null,
    /**
     * 对结果排序
     */
    open var order: PagingOrder? = null,
)
