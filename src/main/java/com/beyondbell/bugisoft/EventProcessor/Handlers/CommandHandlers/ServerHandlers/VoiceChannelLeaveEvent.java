package com.beyondbell.bugisoft.EventProcessor.Handlers.CommandHandlers.ServerHandlers;

import com.beyondbell.bugisoft.Commands.Lobby.ClearChannels;
import com.beyondbell.bugisoft.Commands.Lobby.MovePeople;
import com.beyondbell.bugisoft.EventProcessor.Handlers.EventHandler;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public class VoiceChannelLeaveEvent extends EventHandler {
	private final GuildVoiceLeaveEvent event;


	private VoiceChannelLeaveEvent(final GuildVoiceLeaveEvent voice) {
		super();
		event = voice;
	}
	MovePeople check = new MovePeople();
	protected void handle() {
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
