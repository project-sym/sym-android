package com.ilharper.symri.rxjava

import com.ilharper.symri.entity.SatoriEvent
import com.ilharper.symri.entity.SatoriEventType
import io.reactivex.rxjava3.core.Observable

fun Observable<SatoriEvent>.ofType(type: SatoriEventType): Observable<SatoriEvent> = filter { it.type == type }

fun Observable<SatoriEvent>.ofGuild(guildId: String): Observable<SatoriEvent> = filter { it.guild?.id == guildId }

fun Observable<SatoriEvent>.ofChannel(channelId: String): Observable<SatoriEvent> = filter { it.channel?.id == channelId }

fun Observable<SatoriEvent>.ofUser(userId: String): Observable<SatoriEvent> = filter { it.user?.id == userId }

fun Observable<SatoriEvent>.ofMessage(messageId: String): Observable<SatoriEvent> = filter { it.message?.id == messageId }
