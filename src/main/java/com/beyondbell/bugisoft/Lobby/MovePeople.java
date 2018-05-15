package com.beyondbell.bugisoft.Lobby;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.UserInfo.UserInfoDatabase;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.requests.restaction.GuildAction;

import java.util.List;

public class MovePeople {
	public MovePeople(final GuildVoiceJoinEvent event) {


        GuildController guildController = new GuildController(event.getGuild());

        String category = Bot.settings.getProperty("gameChannelsCategory");
        Category category1 = null;

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
}
