package com.beyondbell.bugisoft.Lobby;

import com.beyondbell.bugisoft.UserInfo.UserInfoDatabase;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.managers.GuildController;

import java.util.List;


public class MovePeople {

	public MovePeople(final GuildVoiceJoinEvent event) {
		if(UserInfoDatabase.findUser(event.getMember().getUser()).
		VoiceChannel lobby = event.getGuild().getVoiceChannelsByName("lobby", true).get(0);
		GuildController server = new GuildController(event.getGuild());

		boolean check = true;
		for (int i = 0; i < server.getGuild().getVoiceChannels().size(); i++) {
			if (server.getGuild().getVoiceChannels().get(i).getName().equals(event.getMember().getGame().toString())) {
				check = false;
				break;
			}
		}

		if (check && !event.getMember().getGame().toString().equals("null")) {
			server.createVoiceChannel(event.getMember().getGame().toString());
			server.moveVoiceMember(event.getMember(), (VoiceChannel) server.getGuild().getVoiceChannelsByName(event.getMember().getGame().toString(), false));
		} else if (event.getMember().getGame().toString().equals("null") || event.getMember().getGame().toString().toLowerCase().equals("Spotify")) {
			server.createVoiceChannel("Spotify");
		} else {
			server.moveVoiceMember(event.getMember(), (VoiceChannel) server.getGuild().getVoiceChannelsByName(event.getMember().getGame().toString(), false));
		}
		
	}
}
