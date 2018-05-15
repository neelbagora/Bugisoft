package com.beyondbell.bugisoft.Lobby;


import com.beyondbell.bugisoft.Bot;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.managers.GuildController;

public class ClearChannels {
    GuildVoiceLeaveEvent event = null;

	public ClearChannels(final GuildVoiceLeaveEvent event) {
	    this.event = event;
        String categoryString = Bot.settings.getProperty("gameChannelsCategory").toLowerCase();


		for(Category findCategory : event.getGuild().getCategories()) {
		    if(findCategory.getName().toLowerCase().equals(categoryString) && findCategory.getVoiceChannels().contains(event.getChannelLeft())
                    && !event.getChannelLeft().getName().toLowerCase().equals("lobby") && event.getChannelLeft().getMembers().size() == 0) {
		        event.getChannelLeft().delete().queue();
		        break;
            }
        }

	}

    public void clearEmpty() {
        for(int i = 0; i < event.getGuild().getVoiceChannels().size(); i++) {
            if(!event.getGuild().getVoiceChannelsByName("lobby",true).get(0).equals(event.getGuild().getVoiceChannels().get(i))
                    && event.getGuild().getVoiceChannels().get(i).getParent().equals(event.getGuild().getVoiceChannelsByName("lobby",true).get(0).getParent())
                    && event.getGuild().getVoiceChannels().get(i).getMembers().size() == 0) {
                    new GuildController(event.getGuild()).getGuild().getVoiceChannels().get(i).delete().queue();
                    break;
                }


        }
    }
}
