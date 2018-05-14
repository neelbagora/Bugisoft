package com.beyondbell.bugisoft.Lobby;

import com.beyondbell.bugisoft.UserInfo.UserInfoDatabase;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.requests.restaction.GuildAction;

import java.util.List;

public class MovePeople {
	public MovePeople(final GuildVoiceJoinEvent event) {
		event.getGuild().getDefaultChannel().sendMessage("MovePeople Accessed").queue();

		GuildController x = new GuildController(event.getGuild());




		/*if (!UserInfoDatabase.findUser(event.getMember().getUser()).getGameShouldMove() || event.getMember().getGame() == null) {
			return;
		}*/

		List<VoiceChannel> target = event.getGuild().getVoiceChannelsByName(event.getMember().getGame().getName(),true);

		if(target == null) {
			Category y = event.getChannelJoined().getParent();
			x.createVoiceChannel(event.getMember().getGame().getName()).setParent(y).queue();

		} else if(target.size() > 0){
			for(int i = 0; i < event.getGuild().getVoiceChannels().size(); i++) {
				if(event.getGuild().getVoiceChannels().get(i).getName().equals(event.getMember().getGame().getName())) {
					x.moveVoiceMember(event.getMember(),event.getGuild().getVoiceChannels().get(i));
					break;
				}
			}
		}
	}
}
