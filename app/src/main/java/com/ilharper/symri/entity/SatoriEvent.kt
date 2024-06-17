package com.ilharper.symri.entity

import com.ilharper.symri.entity.resource.SatoriArgv
import com.ilharper.symri.entity.resource.SatoriButton
import com.ilharper.symri.entity.resource.SatoriChannel
import com.ilharper.symri.entity.resource.SatoriGuild
import com.ilharper.symri.entity.resource.SatoriGuildMember
import com.ilharper.symri.entity.resource.SatoriGuildRole
import com.ilharper.symri.entity.resource.SatoriLogin
import com.ilharper.symri.entity.resource.SatoriMessage
import com.ilharper.symri.entity.resource.SatoriUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class SatoriEvent(
    /**
     * 事件 ID
     */
    var id: Int? = null,
    /**
     * 事件类型
     */
    var type: SatoriEventType? = null,
    /**
     * 接收者的平台名称
     */
    var platform: String? = null,
    /**
     * 接收者的平台账号
     */
    @Json(name = "self_id")
    var selfId: String? = null,
    /**
     * 事件的时间戳
     */
    var timestamp: Long? = null,
    /**
     * 交互指令
     */
    var argv: SatoriArgv? = null,
    /**
     * 交互按钮
     */
    var button: SatoriButton? = null,
    /**
     * 事件所属的频道
     */
    var channel: SatoriChannel? = null,
    /**
     * 事件所属的群组
     */
    var guild: SatoriGuild? = null,
    /**
     * 事件的登录信息
     */
    var login: SatoriLogin? = null,
    /**
     * 事件的目标成员
     */
    var member: SatoriGuildMember? = null,
    /**
     * 事件的消息
     */
    var message: SatoriMessage? = null,
    /**
     * 事件的操作者
     */
    var operator: SatoriUser? = null,
    /**
     * 事件的目标角色
     */
    var role: SatoriGuildRole? = null,
    /**
     * 事件的目标用户
     */
    var user: SatoriUser? = null,
)
