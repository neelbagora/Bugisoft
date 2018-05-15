package com.beyondbell.bugisoft.EventHandling;

import com.beyondbell.bugisoft.EventHandling.Handlers.GuildVoiceJoinEventHandler;
import com.beyondbell.bugisoft.EventHandling.Handlers.GuildVoiceLeaveEventHandler;
import com.beyondbell.bugisoft.EventHandling.Handlers.MessageReceivedEventHandler;
import com.beyondbell.bugisoft.EventHandling.Handlers.ReadyEventHandler;
import com.beyondbell.bugisoft.EventHandling.Handlers.VoiceChannelJoinEvent;
import com.beyondbell.bugisoft.EventHandling.Handlers.VoiceChannelLeaveEvent;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
<<<<<<< HEAD
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
=======
>>>>>>> parent of 86aa9cf... Revert "Merge branch 'master' of https://github.com/LookLotsOfPeople/BugisoftJava"
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class BotEventListener implements EventListener {
	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {
			new MessageReceivedEventHandler((MessageReceivedEvent) event);
		} else if (event instanceof GuildVoiceJoinEvent) {
			new GuildVoiceJoinEventHandler((GuildVoiceJoinEvent) event);
		} else if (event instanceof GuildVoiceLeaveEvent) {
			new GuildVoiceLeaveEventHandler((GuildVoiceLeaveEvent) event);
		} else if (event instanceof ReadyEvent) {
			new ReadyEventHandler((ReadyEvent) event);
		} else if(event instanceof GuildVoiceJoinEvent) {
			new VoiceChannelJoinEvent((GuildVoiceJoinEvent) event);
		} else if(event instanceof GuildVoiceLeaveEvent) {
			new VoiceChannelLeaveEvent((GuildVoiceLeaveEvent) event);
		}
	}
}
