package com.beyondbell.bugisoft;

import com.beyondbell.bugisoft.Ping.Ping;
import com.beyondbell.bugisoft.UserInfo.UserInfoQuerry;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Bot {

	/**
	 * The entrance point of our program.
	 *
	 * @param args The arguments for the program. The first element should be the bot's token.
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Please provide a valid token as the first argument!");
			return;
		}

		// Enable debugging, if no slf4j logger was found
//		LoggerUtil.setDebug(true);

		// The token is the first argument of the program
		String token = args[0];

		// We login blocking, just because it is simpler and doesn't matter here
		DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

		// Print the invite url of the bot
		System.out.println("You can invite me by using the following url: " + api.createBotInvite());

		// Add listeners
		api.addMessageCreateListener(new Ping());
		api.addMessageCreateListener(new UserInfoQuerry());

		// Log a message, if the bot joined or left a server
		api.addServerJoinListener(event -> System.out.println("Joined server " + event.getServer().getName()));
		api.addServerLeaveListener(event -> System.out.println("Left server " + event.getServer().getName()));
	}

}