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

		GuildController guildController = new GuildController(event.getGuild());

		/*if (!UserInfoDatabase.findUser(event.getMember().getUser()).getGameShouldMove() || event.getMember().getGame() == null) {
			return;
		}*/

		//target VoiceChannel that is checked to see if it exists.
		List<VoiceChannel> target;
		String game;
		try {
            game = event.getMember().getGame().getName();
            System.out.println(game);
        } catch (NullPointerException e) {
		    return;
        }

        //checks to see if there exists a voice channel with game name
		target = event.getGuild().getVoiceChannelsByName(game,true);

		//found channel
		if(target.size() == 0) {
		    for(VoiceChannel channel : target) {
		        if(channel.getName().equals(game)) {
		            guildController.moveVoiceMember(event.getMember(), channel).queue();
		            break;
		        }
		    }
		    System.out.println("GameFound");
		    //target channel not found
        } else {
		    Category category = event.getChannelJoined().getParent();
		    //create new voice channel if game not found
            guildController.createVoiceChannel(game).setParent(category).queue();
            System.out.println("gameCreated");
            //find created voice channel and move to that
            for(VoiceChannel channel : event.getGuild().getVoiceChannels()) {
                if(channel.getName().equals(game)) {
                    guildController.moveVoiceMember(event.getMember(), channel).queue();
                    break;
                }

            }
		}
	}
}
