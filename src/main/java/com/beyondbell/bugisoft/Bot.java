package com.beyondbell.bugisoft;

import com.beyondbell.bugisoft.AdminCommands.CopyAvatar;
import com.beyondbell.bugisoft.Ping.Ping;
import com.beyondbell.bugisoft.Ping.PingReceiver;
import com.beyondbell.bugisoft.UserInfo.UserInfoQuerry;
import com.beyondbell.bugisoft.UserInfo.UserInfoRegister;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Bot {

	/**
	 * The entrance point of our program.
	 *
	 * @param args The arguments for the program. The first element should be the bot's token.
	 */
	public static void main(String[] args) {
		String token = ""; // TODO Make This Read From a File

		DiscordApi client = new DiscordApiBuilder().setToken(token).login().join();

		// Ping
		client.addMessageCreateListener(new Ping());
		client.addMessageCreateListener(new PingReceiver());

		// User Info
		client.addMessageCreateListener(new UserInfoRegister());
		client.addMessageCreateListener(new UserInfoQuerry());

		// Admin Commands
		client.addMessageCreateListener(new CopyAvatar());

		// Other
		client.addMessageCreateListener(new TestCommand()); // TODO Remove
	}

}