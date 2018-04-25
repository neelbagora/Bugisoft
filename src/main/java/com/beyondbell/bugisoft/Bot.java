package com.beyondbell.bugisoft;

import com.beyondbell.bugisoft.AdminCommands.CopyAvatar;
import com.beyondbell.bugisoft.Ping.Ping;
import com.beyondbell.bugisoft.Ping.PingReceiver;
import com.beyondbell.bugisoft.Tournament.TournamentTest;
import com.beyondbell.bugisoft.UserInfo.UserInfoQuery;
import com.beyondbell.bugisoft.UserInfo.UserInfoRegister;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class Bot {
	private static DiscordApi client;
	private static String token;

	public static void main(String[] args) {
		loadProperties();
		initBot();
		addListeners();
		token = null;
		// Anything Below This Line Should NOT Be Pushed
		client.addMessageCreateListener(new TournamentTest());
	}

	private static void loadProperties() {
		Properties botProperties = new Properties();
		FileInputStream botPropertiesFile;
		try {
			botPropertiesFile = new FileInputStream("botProperties");
			botProperties.load(botPropertiesFile);
			botPropertiesFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		token = String.valueOf(botProperties.get("token"));
	}

	private static void initBot() {
		client = new DiscordApiBuilder().setToken(token).login().join();
	}

	private static void addListeners() {
		addAdminListeners();
		addUserInfoListeners();
		addPingListeners();
	}

	private static void addAdminListeners() {
		client.addMessageCreateListener(new CopyAvatar());
	}

	private static void addUserInfoListeners() {
		client.addMessageCreateListener(new UserInfoRegister());
		client.addMessageCreateListener(new UserInfoQuery());
	}

	private static void addPingListeners() {
		client.addMessageCreateListener(new Ping());
		client.addMessageCreateListener(new PingReceiver());
	}

}