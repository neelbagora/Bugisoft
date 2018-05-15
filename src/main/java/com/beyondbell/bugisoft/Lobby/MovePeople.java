package com.beyondbell.bugisoft.Lobby;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.UserInfo.UserInfoDatabase;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.requests.restaction.GuildAction;

import java.util.List;

public class MovePeople {

	public MovePeople(final GuildVoiceJoinEvent event) {
        GuildController guildController = new GuildController(event.getGuild());
        String category = Bot.settings.getProperty("gameChannelsCategory");
        Category category1 = event.getChannelJoined().getParent();

        for(Category list : event.getGuild().getCategories()) {
            if(list.getName().equals(category)) {
                category1 = list;
                break;
            }
        }

        if(event.getMember().getGame() == null) {
            return;
        }

        for(VoiceChannel channel : event.getGuild().getVoiceChannels()) {
            if(channel.getName().equals(event.getMember().getGame().getName())) {
                guildController.moveVoiceMember(event.getMember(), channel).queue();
                break;
            }
        }

        guildController.createVoiceChannel(event.getMember().getGame().getName()).setParent(category1).queue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
            if(voiceChannel.getName().equals(event.getMember().getGame().getName())) {
                guildController.moveVoiceMember(event.getMember(), voiceChannel).queue();
                break;
            }
        }
	}

	public MovePeople(UserUpdateGameEvent event) {
	    //category under where the channel must be created
	    Category category = null;

	    if(event.getNewGame() == null) {
	        VoiceChannel x = event.getMember().getVoiceState().getChannel();
	        new GuildController(event.getGuild()).moveVoiceMember(event.getMember(), event.getGuild().getVoiceChannelsByName("lobby",true).get(0)).queue();
	        try {
	            Thread.sleep(500);
            } catch (InterruptedException e) {
	            e.printStackTrace();
            }
	        x.delete().queue();
	        return;
        }

	    //if user is connected
	    if(event.getMember().getVoiceState().inVoiceChannel()) {
	        //set category
	        category = event.getMember().getVoiceState().getChannel().getParent();
	        for(VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
	            if(voiceChannel.getName().equals(event.getNewGame().getName())) {
	                new GuildController(event.getGuild()).moveVoiceMember(event.getMember(), voiceChannel).queue();
	                return;
                }
            }

            try {
                Thread.sleep(300);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            new GuildController(event.getGuild()).createVoiceChannel(event.getNewGame().getName()).setParent(category).queue();
	        try {
	            Thread.sleep(300);
            } catch(InterruptedException e) {
	            e.printStackTrace();
            }
            for(VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
                if(voiceChannel.getName().equals(event.getNewGame().getName())) {
                    new GuildController(event.getGuild()).moveVoiceMember(event.getMember(), voiceChannel).queue();
                    return;
                }
            }
        }



    }

}
