package com.beyondbell.bugisoft.EventHandling.Handlers;

import com.beyondbell.bugisoft.Lobby.MovePeople;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;

public class VoiceChannelJoinEvent  extends EventHandler {
	private final GuildVoiceJoinEvent event;

	public VoiceChannelJoinEvent(final GuildVoiceJoinEvent event) {
		super();
		this.event = event;
	}

	@Override
	void handle() {
		MovePeople check = new MovePeople();
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
	}
}
