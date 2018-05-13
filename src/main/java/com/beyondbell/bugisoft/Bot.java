package com.beyondbell.bugisoft;

import com.beyondbell.bugisoft.EventHandling.BotEventListener;
import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Bot {
	public static void main(String[] args) {
		// Loads Bot Properties
		final Properties botProperties = new Properties();
		try {
			final FileInputStream botPropertiesFile = new FileInputStream("token");
			botProperties.load(botPropertiesFile);
			botPropertiesFile.close();
		} catch (IOException e) {
			System.out.println("Cannot Find Token File!");
			return;
		}

		// Initializes the Bot
		try {
			new JDABuilder(AccountType.BOT)
					.setToken(String.valueOf(botProperties.getProperty("token")))
					.setAutoReconnect(true)
					.addEventListener(new BotEventListener())
					.setAudioEnabled(true)
					.setAudioSendFactory(new NativeAudioSendFactory())
					.setAutoReconnect(true)
					.setCompressionEnabled(false)
					.buildAsync();
		} catch (LoginException e) {
			System.out.println("Please Place the Correct Token Inside of the Bot Properties File");
		}
	}
}
