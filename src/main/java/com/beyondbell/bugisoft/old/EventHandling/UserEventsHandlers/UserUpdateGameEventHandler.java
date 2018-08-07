package com.beyondbell.bugisoft.old.EventHandling.UserEventsHandlers;

import com.beyondbell.bugisoft.old.Bot;
import com.beyondbell.bugisoft.old.EventHandling.EventHandler;
import com.beyondbell.bugisoft.old.Lobby.MovePeople;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;

public class UserUpdateGameEventHandler extends EventHandler {
	private final UserUpdateGameEvent event;

	public UserUpdateGameEventHandler(UserUpdateGameEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected void handle() {
		if (Bot.SETTINGS.getProperty("temporaryChannelsCategory") != null && event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).size() != 0) {
			for (final VoiceChannel voiceChannel : event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0).getVoiceChannels()) {
				for (Member member : voiceChannel.getMembers()) {
					if (event.getMember() == member) {
						new MovePeople(event);
						break;
					}
				}
			}
		} else {
			Bot.LOGGER.warn("No Temporary Channel Category!");
		}
	}
}
