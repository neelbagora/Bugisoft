package com.beyondbell.bugisoft.EventHandling.GuildEventHandlers;

import com.beyondbell.bugisoft.RockPaperScissors.RockPaperScissors;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class GameModeEventHandler {
	boolean value;
	User author;

	public GameModeEventHandler(GuildMessageReceivedEvent event, boolean change) {
		value = change;
		author = event.getAuthor();
	}

	public GameModeEventHandler(GuildMessageReceivedEvent event) {
		if(value && event.getAuthor() == author) {
			new RockPaperScissors(event);
		}
	}
}
