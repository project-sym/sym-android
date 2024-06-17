package com.ilharper.symri.entity

import com.squareup.moshi.Json

enum class SatoriEventType(val value: String) {
    /**
     * 内部事件
     */
    @Json(name = "internal")
    INTERNAL("internal"),

    /**
     * 加入群组
     */
    @Json(name = "guild-added")
    GUILD_ADDED("guild-added"),

    /**
     * 群组被修改
     */
    @Json(name = "guild-updated")
    GUILD_UPDATED("guild-updated"),

    /**
     * 退出群组
     */
    @Json(name = "guild-removed")
    GUILD_REMOVED("guild-removed"),

    /**
     * 接收到新的入群邀请
     */
    @Json(name = "guild-request")
    GUILD_REQUEST("guild-request"),

    /**
     * 群组成员增加
     */
    @Json(name = "guild-member-added")
    GUILD_MEMBER_ADDED("guild-member-added"),

    /**
     * 群组成员信息更新
     */
    @Json(name = "guild-member-updated")
    GUILD_MEMBER_UPDATED("guild-member-updated"),

    /**
     * 群组成员移除
     */
    @Json(name = "guild-member-removed")
    GUILD_MEMBER_REMOVED("guild-member-removed"),

    /**
     * 接收到新的加群请求
     */
    @Json(name = "guild-member-request")
    GUILD_MEMBER_REQUEST("guild-member-request"),

    /**
     * 群组角色被创建
     */
    @Json(name = "guild-role-created")
    GUILD_ROLE_CREATED("guild-role-created"),

    /**
     * 群组角色被修改
     */
    @Json(name = "guild-role-updated")
    GUILD_ROLE_UPDATED("guild-role-updated"),

    /**
     * 群组角色被删除
     */
    @Json(name = "guild-role-deleted")
    GUILD_ROLE_DELETED("guild-role-deleted"),

    /**
     * 类型为 action 的按钮被点击
     */
    @Json(name = "interaction/button")
    INTERACTION_BUTTON("interaction/button"),

    /**
     * 调用斜线指令
     */
    @Json(name = "interaction/command")
    INTERACTION_COMMAND("interaction/command"),

    /**
     * 登录被创建
     */
    @Json(name = "login-added")
    LOGIN_ADDED("login-added"),

    /**
     * 登录被删除
     */
    @Json(name = "login-removed")
    LOGIN_REMOVED("login-removed"),

    /**
     * 登录信息更新
     */
    @Json(name = "login-updated")
    LOGIN_UPDATED("login-updated"),

    /**
     * 消息被创建
     */
    @Json(name = "message-created")
    MESSAGE_CREATED("message-created"),

    /**
     * 消息被编辑
     */
    @Json(name = "message-updated")
    MESSAGE_UPDATED("message-updated"),

    /**
     * 消息被删除
     */
    @Json(name = "message-deleted")
    MESSAGE_DELETED("message-deleted"),

    /**
     * 表态被添加
     */
    @Json(name = "reaction-added")
    REACTION_ADDED("reaction-added"),

    /**
     * 表态被移除
     */
    @Json(name = "reaction-removed")
    REACTION_REMOVED("reaction-removed"),

    /**
     * 接收到新的好友申请
     */
    @Json(name = "friend-request")
    FRIEND_REQUEST("friend-request"),
}
