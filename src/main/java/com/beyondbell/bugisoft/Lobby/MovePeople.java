package com.beyondbell.bugisoft.Lobby;

import com.beyondbell.bugisoft.UserInfo.UserInfoDatabase;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.requests.restaction.GuildAction;

public class MovePeople {
	public MovePeople(final GuildVoiceJoinEvent event) {
		if (!UserInfoDatabase.findUser(event.getMember().getUser()).getGameShouldMove() || event.getMember().getGame() == null) {
			return;
		}

		VoiceChannel target = event.getGuild().getVoiceChannelsByName(event.getMember().getGame().getName(),true).get(0);
		if(target == null) {
			new GuildAction(event.getJDA(), "channelCreator")
					.addChannel(new GuildAction.ChannelData(ChannelType.VOICE, event.getMember().getGame().getName()))
					.complete();
			new GuildController(event.getGuild()).moveVoiceMember(event.getMember(), event.getGuild()
					.getVoiceChannelsByName(event.getMember().getGame().getName(),true).get(0)).queue();
		} else {
			new GuildController(event.getGuild()).moveVoiceMember(event.getMember(), target).queue();
		}
	}
}
