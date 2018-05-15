package com.beyondbell.bugisoft.Lobby;

import com.beyondbell.bugisoft.Bot;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.entities.Category;

public class ClearChannels {
	public ClearChannels(GuildVoiceLeaveEvent event) {
        String categoryString = Bot.settings.getProperty("gameChannelsCategory");
		for(Category category : event.getGuild().getCategories()) {
		    if(category.getName().equals(categoryString) && category.getVoiceChannels().contains(event.getChannelLeft())
                    && !event.getChannelLeft().getName().toLowerCase().equals("lobby")) {
		        event.getChannelLeft().delete().queue();
            }
        }

	}
}
