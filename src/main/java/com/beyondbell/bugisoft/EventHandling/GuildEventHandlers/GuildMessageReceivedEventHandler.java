package com.beyondbell.bugisoft.EventHandling.GuildEventHandlers;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.EventHandling.EventHandler;
import com.beyondbell.bugisoft.Logger.Commands.Report;
import com.beyondbell.bugisoft.Logger.LoggerDatabase;
import com.beyondbell.bugisoft.Minigames.GameModeEventHandler;
import com.beyondbell.bugisoft.Music.Commands.ListQueue;
import com.beyondbell.bugisoft.Music.Commands.PausePlayer;
import com.beyondbell.bugisoft.Music.Commands.PlaySong;
import com.beyondbell.bugisoft.Music.Commands.SkipTrack;
import com.beyondbell.bugisoft.ProfanityFilter.AddProfanityWords;
import com.beyondbell.bugisoft.ProfanityFilter.ProfanityFilter;
import com.beyondbell.bugisoft.Standalone.AdminCommands.Update;
import com.beyondbell.bugisoft.Standalone.Commands.Invite.CreateInvite;
import com.beyondbell.bugisoft.Standalone.Commands.Ping.Ping;
import com.beyondbell.bugisoft.UserInfo.UserInfoQuery;
import com.beyondbell.bugisoft.Utilities.TextFormatters.ParametersFormatter;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public final class GuildMessageReceivedEventHandler extends EventHandler {
	private final GuildMessageReceivedEvent event;

	public GuildMessageReceivedEventHandler(final GuildMessageReceivedEvent event) {
		super();
		this.event = event;
		if(event.getMessage().getContentRaw().toLowerCase().contains("$rock") || event.getMessage().getContentRaw().toLowerCase().contains("$scissors") || event.getMessage().getContentRaw().contains("$paper")) {
			new GameModeEventHandler(event);
		}

	}

	@Override
	protected final void handle() {
		if (Bot.SETTINGS.getProperty("log").equalsIgnoreCase("true")) {
			LoggerDatabase.logEvent(event);
		}

		// Checks if Message is Empty
		if (event.getMessage().getContentRaw().length() == 0) {
			return;
		}

		// Checks if It is Bot
		if (event.getAuthor().isBot()) {
			if (event.getMessage().getContentRaw().equals("Ping Calculating...")) {
				Ping.pingReceived(event);
			} else {
				return;
			}
		}

		if (event.getAuthor().getId().equals("119978889891151876")) {   // Admin Commands
			if (event.getMessage().getContentRaw().equals("UPDATE apple")) {
				event.getMessage().delete().complete();
				new Update(event.getJDA());
				return;
			}
		}

		// Checks for Prefixes
		if (event.getMessage().getContentRaw().charAt(0) != '!' && event.getMessage().getContentRaw().charAt(0) != ';' && event.getMessage().getContentRaw().charAt(0) != '^' && event.getMessage().getContentRaw().charAt(0) != '$') {
			if (Bot.SETTINGS.getProperty("profanityFilter").equalsIgnoreCase("true")) {
				// Checks for Profanity
				new ProfanityFilter(event);
			} else if (Bot.SETTINGS.getProperty("profanityFilter").equalsIgnoreCase("false")) {
				// Nothing
			} else {
				System.out.println("Key: " + "profanityFilter" + "\tin Properties File: " + "SETTINGS" + "\tis not configured correctly!");
			}
			return;
		}

		// Converts to Parameters
		final String[] parameters = ParametersFormatter.stringToParameters(event.getMessage().getContentRaw());

		// Checks for Command
		switch (parameters[0]) {    // Prefix Checker
			case "!":   // Guild Related Commands
				switch (parameters[1].toLowerCase()) {

					case "ping":    // Ping
						synchronized (event) {
							Ping.ping(event);
							event.getMessage().delete().queue();
						}
						break;
					case "invite":
						synchronized (event) {
							if (parameters.length == 3) {
								new CreateInvite(event, parameters[2]);
							} else {
								new CreateInvite(event, "1");
							}
							event.getMessage().delete().queue();
						}
					case "report" : //report
						synchronized (event) {
							if(parameters.length == 4) {
								int number;
								try {
									number = Integer.parseInt(parameters[3]);
								} catch (NumberFormatException error) {
									number = 1;
								}
								new Report(event, parameters[2], number);
							} else {
								event.getChannel().sendMessage("Follow format: !report nickname numberOfMessages").queue();
							}
							event.getMessage().delete().queue();
						}

					case "add" :
						synchronized (event) {
							if(parameters.length == 3) {
								new AddProfanityWords(parameters[2]);
							}
						}
					default:    // Not a Guild Related Command
						break;
				}
				break;
			case "/":   // User Related Commands
				switch (parameters[1]) {
					case "query":
						synchronized (event) {
							new UserInfoQuery(event);
							event.getMessage().delete().queue();
						}
						break;
					default:    // Not a User Related Command
						break;
				}
				break;
			case "^":
				switch (parameters[1]) {
					case "play":
						synchronized (event) {
							if (parameters.length == 3) {
								new PlaySong(event, parameters[2]);
							} else {
								// TODO Handle Wrong Amount of Params
							}
							event.getMessage().delete().queue();
						}
						break;
					case "pause":
						synchronized (event) {
							new PausePlayer(event, true);
							event.getMessage().delete().queue();
						}
						break;
					case "unpause":
						synchronized (event) {
							new PausePlayer(event, false);
							event.getMessage().delete().queue();
						}
						break;
					case "skip":
						synchronized (event) {
							if (parameters.length == 3) {
								new SkipTrack(event, parameters[2]);
							} else {
								new SkipTrack(event);
							}
							event.getMessage().delete().queue();
						}
						break;
					case "list":
						synchronized (event) {
							new ListQueue(event);
							event.getMessage().delete().queue();
						}
					default:
						break;
				}
			case "$" :
				switch(parameters[1].toLowerCase()) {
					case "start":
						new GameModeEventHandler(event, event.getAuthor().getId(),true);

						break;
					case "quit":
						new GameModeEventHandler(event, event.getAuthor().getId(),false);
						new GameModeEventHandler(event);
						break;
					default:
						break;
				}


			default:    // Not a Valid Prefix
				break;
		}
	}
}
