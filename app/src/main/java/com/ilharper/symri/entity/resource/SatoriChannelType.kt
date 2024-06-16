package com.ilharper.symri.entity.resource

import com.ilharper.symri.moshi.IEnumValue

enum class SatoriChannelType(override val value: Int) : IEnumValue {
    /**
     * 文本频道
     */
    TEXT(0),

    /**
     * 私聊频道
     */
    DIRECT(1),

    /**
     * 分类频道
     */
    CATEGORY(2),

    /**
     * 语音频道
     */
    VOICE(3),
}
