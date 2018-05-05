package com.beyondbell.bugisoft.EventProcessor.Handlers.CommandHandlers.ServerHandlers;

import com.beyondbell.bugisoft.Commands.Lobby.MovePeople;
import com.beyondbell.bugisoft.EventProcessor.Handlers.EventHandler;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;

public class VoiceChannelJoinEvent  extends EventHandler {
	private final GuildVoiceJoinEvent event;


	private VoiceChannelJoinEvent(final GuildVoiceJoinEvent voice) {
		super();
		event = voice;
	}

	MovePeople check = new MovePeople();
	protected void handle() {
		if(check.getStatus()) {
			String id = check.getLobby();
			if(id == null) {
				event.getGuild().getDefaultChannel().sendMessage("Set lobby using !setLobby lobbyID first");
			} else {
				if(event.getMember().getVoiceState().getAudioChannel().getId().equals(id)) {
					new MovePeople(event);
				}
			}
		}
		check = null;
	}
}
