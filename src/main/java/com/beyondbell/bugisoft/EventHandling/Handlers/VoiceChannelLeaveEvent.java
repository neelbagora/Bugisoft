package com.beyondbell.bugisoft.EventHandling.Handlers;

import com.beyondbell.bugisoft.Lobby.ClearChannels;
import com.beyondbell.bugisoft.Lobby.MovePeople;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public class VoiceChannelLeaveEvent extends EventHandler {
	private final GuildVoiceLeaveEvent event;

	public VoiceChannelLeaveEvent(final GuildVoiceLeaveEvent voice) {
		super();
		event = voice;
	}

	@Override
	void handle() {
		MovePeople check = new MovePeople();
		if(check.getLobby() != null) {
			if(!event.getChannelLeft().equals(check.getLobby()) && event.getChannelLeft().getMembers().size() == 0) {
				new ClearChannels(event);
			}
		} else {
			event.getGuild().getDefaultChannel().sendMessage("Set lobby using !setLobby lobbyID");
		}
		check = null;
	}

}
