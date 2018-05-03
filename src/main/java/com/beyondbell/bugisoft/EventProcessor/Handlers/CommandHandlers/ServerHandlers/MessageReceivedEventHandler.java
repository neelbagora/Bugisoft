package com.beyondbell.bugisoft.EventProcessor.Handlers.CommandHandlers.ServerHandlers;

import com.beyondbell.bugisoft.Commands.Invite.CreateInvite;
import com.beyondbell.bugisoft.Commands.Ping.Ping;
import com.beyondbell.bugisoft.Commands.Report.Report;
import com.beyondbell.bugisoft.Databases.UserInfo.UserInfoQuery;
import com.beyondbell.bugisoft.EventProcessor.Handlers.EventHandler;
import com.beyondbell.bugisoft.Music.Commands.*;
import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageReceivedEventHandler extends EventHandler {
	private final MessageReceivedEvent event;

	public MessageReceivedEventHandler(final MessageReceivedEvent event) {
		super();
		this.event = event;
	}

	@Override
	public void handle() {
		// Checks if Message is Empty
		if (event.getMessage().getContentRaw().length() == 0) {
			return;
		}

		// Checks if It is Bot
		if (event.getAuthor().isBot()) {
			if (event.getMessage().getContentRaw().equals("Ping Calculating...")) {
				Ping.pingReceived(event);
			}
		}

		// Checks for Prefix
		if (event.getMessage().getContentRaw().charAt(0) != '!' && event.getMessage().getContentRaw().charAt(0) != ';' && event.getMessage().getContentRaw().charAt(0) != '^') {
			return;
		}

		// Converts to Parameters
		String[] parameters = InputFormatter.stringToParameters(event.getMessage().getContentRaw());

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
							if(parameters.length == 3) {
								int number;
								try {
									number = Integer.parseInt(parameters[3]);
								} catch (NumberFormatException error) {
									number = 1;
								}
								new Report(event, number, parameters[2]);
							} else {
								event.getTextChannel().sendMessage("Follow format: !report nickname numberOfMessages").queue();
							}
							event.getMessage().delete().queue();
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
