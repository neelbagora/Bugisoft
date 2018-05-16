package com.beyondbell.bugisoft.EventHandling.GuildEventHandlers;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.EventHandling.EventHandler;
import com.beyondbell.bugisoft.Lobby.ClearChannels;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public final class GuildVoiceLeaveEventHandler extends EventHandler {
	private final GuildVoiceLeaveEvent event;

	public GuildVoiceLeaveEventHandler(final GuildVoiceLeaveEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected final void handle() {
		if(Bot.settings.getProperty("defaultTempChannel") == "") {
			event.getGuild().getDefaultChannel().sendMessage("Lobby not set");
		} else {
			new ClearChannels(event);
			new ClearChannels(event).clearEmpty();
		}
	}
}
