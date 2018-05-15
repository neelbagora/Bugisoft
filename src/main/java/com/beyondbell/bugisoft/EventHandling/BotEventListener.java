package com.beyondbell.bugisoft.EventHandling;

import com.beyondbell.bugisoft.EventHandling.Handlers.*;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public final class BotEventListener implements EventListener {
	@Override
	public final void onEvent(final Event event) {
		if (event instanceof GuildMessageReceivedEvent) {
			new GuildMessageReceivedEventHandler((GuildMessageReceivedEvent) event);
		} else if (event instanceof GuildVoiceJoinEvent) {
			new GuildVoiceJoinEventHandler((GuildVoiceJoinEvent) event);
		} else if (event instanceof GuildVoiceLeaveEvent) {
			new GuildVoiceLeaveEventHandler((GuildVoiceLeaveEvent) event);
		} else if (event instanceof ReadyEvent) {
			new ReadyEventHandler((ReadyEvent) event);
		} else if(event instanceof UserUpdateGameEvent) {
			new GameUpdateHandler((UserUpdateGameEvent) event);
		}
	}
}
