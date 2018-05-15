package com.beyondbell.bugisoft.Lobby;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.entities.Category;

public class ClearChannels {
	public ClearChannels(GuildVoiceLeaveEvent event) {
<<<<<<< HEAD
		for(Category category : event.getGuild().getCategories()) {
		    if(category.getName().toLowerCase().equals("temporary channels") && category.getVoiceChannels().contains(event.getChannelLeft())) {
		        event.getChannelLeft().delete().queue();
            }
        }
=======
		event.getChannelLeft().delete().queue();
>>>>>>> parent of 86aa9cf... Revert "Merge branch 'master' of https://github.com/LookLotsOfPeople/BugisoftJava"
	}
}
