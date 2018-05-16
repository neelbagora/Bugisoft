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

    String category = Bot.settings.getProperty("gameChannelsCategory").toLowerCase();
    Category category1;
    VoiceChannel lobby;

	public MovePeople(final GuildVoiceJoinEvent event) {
        lobby = event.getGuild().getVoiceChannelsByName(Bot.settings.getProperty("defaultTempChannel").toLowerCase(),true).get(0);

        GuildController guildController = new GuildController(event.getGuild());
        if(event.getGuild().getCategoriesByName(category, true).size() > 0) {
            category1 = event.getGuild().getCategoriesByName(category, true).get(0);
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
        if(event.getGuild().getCategoriesByName(category, true).size() > 0) {
            guildController.createVoiceChannel(event.getMember().getGame().getName()).setParent(category1).queue();
        } else {
            guildController.createVoiceChannel(event.getMember().getGame().getName()).queue();
        }

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
	    GuildController controller = new GuildController(event.getGuild());
	    //category under where the channel must be created
	    if(event.getGuild().getCategoriesByName(category,true).size() > 0) {
            category1 = event.getGuild().getCategoriesByName(category, true).get(0);
        } else {
	        category1 = null;
        }


	    if(event.getNewGame() == null) {
	        VoiceChannel x = event.getMember().getVoiceState().getChannel();
	        if(event.getMember().getVoiceState().inVoiceChannel()) {
                controller.moveVoiceMember(event.getMember(), event.getGuild().getVoiceChannelsByName("lobby",true).get(0)).queue();
            } else {
	            return;
            }
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
	        for(VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
	            if(voiceChannel.getName().equals(event.getNewGame().getName())) {
	                controller.moveVoiceMember(event.getMember(), voiceChannel).queue();
	                break;
	            }
            }

            try {
                Thread.sleep(300);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            new GuildController(event.getGuild()).createVoiceChannel(event.getNewGame().getName()).setParent(category1).queue();
	        try {
	            Thread.sleep(300);
            } catch(InterruptedException e) {
	            e.printStackTrace();
            }
            for(VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
                if(voiceChannel.getName().equals(event.getNewGame().getName())) {
                    controller.moveVoiceMember(event.getMember(), voiceChannel).queue();
                    return;
                }
            }
        }



    }

}
