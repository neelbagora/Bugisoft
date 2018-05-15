package com.beyondbell.bugisoft.EventHandling;

import com.beyondbell.bugisoft.EventHandling.Handlers.GuildVoiceJoinEventHandler;
import com.beyondbell.bugisoft.EventHandling.Handlers.GuildVoiceLeaveEventHandler;
import com.beyondbell.bugisoft.EventHandling.Handlers.MessageReceivedEventHandler;
import com.beyondbell.bugisoft.EventHandling.Handlers.ReadyEventHandler;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class BotEventListener implements EventListener {
	@Override
	public void onEvent(final Event event) {
		if (event instanceof MessageReceivedEvent) {
			new MessageReceivedEventHandler((MessageReceivedEvent) event);
		} else if (event instanceof GuildVoiceJoinEvent) {
			new GuildVoiceJoinEventHandler((GuildVoiceJoinEvent) event);
		} else if (event instanceof GuildVoiceLeaveEvent) {
			new GuildVoiceLeaveEventHandler((GuildVoiceLeaveEvent) event);
		} else if (event instanceof ReadyEvent) {
			new ReadyEventHandler((ReadyEvent) event);
		}
	}
}
