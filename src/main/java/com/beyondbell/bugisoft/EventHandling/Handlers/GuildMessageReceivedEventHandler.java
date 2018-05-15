package com.beyondbell.bugisoft.EventHandling.Handlers;

import com.beyondbell.bugisoft.Logger.Commands.Report;
import com.beyondbell.bugisoft.Music.Commands.*;
import com.beyondbell.bugisoft.Standalone.AdminCommands.Update;
import com.beyondbell.bugisoft.Standalone.Commands.Invite.CreateInvite;
import com.beyondbell.bugisoft.Standalone.Commands.Ping.Ping;
import com.beyondbell.bugisoft.UserInfo.UserInfoQuery;
import com.beyondbell.bugisoft.Utilities.MessageUtilities.AddProfanityWords;
import com.beyondbell.bugisoft.Utilities.MessageUtilities.ProfanityFilter;
import com.beyondbell.bugisoft.Utilities.TextFormatters.ParametersFormatter;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public final class GuildMessageReceivedEventHandler extends EventHandler {
	private final GuildMessageReceivedEvent event;

	public GuildMessageReceivedEventHandler(final GuildMessageReceivedEvent event) {
		super();
		this.event = event;
	}

	@Override
	final void handle() {
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
				new Update();
				return;
			}
		}

		// Checks for Prefixes
		if (event.getMessage().getContentRaw().charAt(0) != '!' && event.getMessage().getContentRaw().charAt(0) != ';' && event.getMessage().getContentRaw().charAt(0) != '^') {
			// Checks for Profanity
			new ProfanityFilter(event);
			return;
		}

		// Converts to Parameters
		final String[] parameters = ParametersFormatter.stringToParameters(event.getMessage().getContentRaw());

		// Checks for Command
		switch (parameters[0]) {    // Prefix Checker
			case "!":   // Guild Related Commands
				switch (parameters[1]) {
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
						/*
					case "setLobby":
						synchronized (event) {
							if(parameters.length == 3) {
								try {
									event.getGuild().getVoiceChannelById(parameters[3]);

								} catch (NullPointerException notFound) {
									event.getTextChannel().sendMessage("Invalid Voice channel ID");
								}
							} else {
								event.getTextChannel().sendMessage("Please set default lobby: !report lobbyID");
							}

						}
						break;
					*/
					/*case "off":
						synchronized (event) {
							if(parameters.length == 3) {

								event.getTextChannel().sendMessage("Automatic moving off");
							} else {
								event.getTextChannel().sendMessage("To turn off: !move off");
							}

						}
						break;
					case "on":
						synchronized (event) {
							if(parameters.length == 3) {
								event.getTextChannel().sendMessage("Automatic moving on");
							} else {
								event.getTextChannel().sendMessage("To turn on: !move on");
							}

						}
						break; */
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
					case "join":
						synchronized (event) {
							new JoinVoice(event);
							event.getMessage().delete().queue();
						}
						break;
					case "leave":
						synchronized (event) {
							new LeaveVoice(event);
							event.getMessage().delete().queue();
						}
						break;
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
				break;
			default:    // Not a Valid Prefix
				break;
		}
	}
}
