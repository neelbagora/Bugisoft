package com.beyondbell.bugisoft.Lobby;


import com.beyondbell.bugisoft.Bot;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.managers.GuildController;

public class ClearChannels {
    GuildVoiceLeaveEvent event;
    Category category;
    VoiceChannel lobby;

	public ClearChannels(final GuildVoiceLeaveEvent event) {
        try {
            lobby = event.getGuild().getVoiceChannelsByName(Bot.settings.getProperty("defaultTempChannel").toLowerCase(),true).get(0);
        } catch(NullPointerException e) {
            event.getGuild().getDefaultChannel().sendMessage("Default Lobby not set").queue();
            return;
        }
	    this.event = event;
        String categoryString = Bot.settings.getProperty("gameChannelsCategory").toLowerCase();
        category = event.getGuild().getCategoriesByName(categoryString, true).get(0);

	}

    public void clearEmpty() {
            for(int i = 0; i < event.getGuild().getVoiceChannels().size(); i++) {
                if (!lobby.equals(event.getGuild().getVoiceChannels().get(i))
                        && event.getGuild().getVoiceChannels().get(i).getParent().equals(category)
                        && event.getGuild().getVoiceChannels().get(i).getMembers().size() == 0) {
                    new GuildController(event.getGuild()).getGuild().getVoiceChannels().get(i).delete().queue();
                }
            }


    }
}
