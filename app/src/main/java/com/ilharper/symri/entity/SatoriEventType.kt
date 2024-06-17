package com.ilharper.symri.entity

enum class SatoriEventType(val value: String) {
    /**
     * 加入群组
     */
    GUILD_ADDED("guild-added"),

    /**
     * 群组被修改
     */
    GUILD_UPDATED("guild-updated"),

    /**
     * 退出群组
     */
    GUILD_REMOVED("guild-removed"),

    /**
     * 接收到新的入群邀请
     */
    GUILD_REQUEST("guild-request"),

    /**
     * 群组成员增加
     */
    GUILD_MEMBER_ADDED("guild-member-added"),

    /**
     * 群组成员信息更新
     */
    GUILD_MEMBER_UPDATED("guild-member-updated"),

    /**
     * 群组成员移除
     */
    GUILD_MEMBER_REMOVED("guild-member-removed"),

    /**
     * 接收到新的加群请求
     */
    GUILD_MEMBER_REQUEST("guild-member-request"),

    /**
     * 群组角色被创建
     */
    GUILD_ROLE_CREATED("guild-role-created"),

    /**
     * 群组角色被修改
     */
    GUILD_ROLE_UPDATED("guild-role-updated"),

    /**
     * 群组角色被删除
     */
    GUILD_ROLE_DELETED("guild-role-deleted"),

    /**
     * 类型为 action 的按钮被点击
     */
    INTERACTION_BUTTON("interaction/button"),

    /**
     * 调用斜线指令
     */
    INTERACTION_COMMAND("interaction/command"),

    /**
     * 登录被创建
     */
    LOGIN_ADDED("login-added"),

    /**
     * 登录被删除
     */
    LOGIN_REMOVED("login-removed"),

    /**
     * 登录信息更新
     */
    LOGIN_UPDATED("login-updated"),

    /**
     * 消息被创建
     */
    MESSAGE_CREATED("message-created"),

    /**
     * 消息被编辑
     */
    MESSAGE_UPDATED("message-updated"),

    /**
     * 消息被删除
     */
    MESSAGE_DELETED("message-deleted"),

    /**
     * 表态被添加
     */
    REACTION_ADDED("reaction-added"),

    /**
     * 表态被移除
     */
    REACTION_REMOVED("reaction-removed"),

    /**
     * 接收到新的好友申请
     */
    FRIEND_REQUEST("friend-request"),
}
