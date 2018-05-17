package com.beyondbell.bugisoft;

import com.beyondbell.bugisoft.EventHandling.BotEventListener;
import com.beyondbell.bugisoft.Music.Music;
import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Bot {
	public final static Properties SETTINGS = new Properties();
	public final static Logger LOGGER = LogManager.getRootLogger();

	public static void main(final String... args) {
		// Loads Settings
		try {
			final FileInputStream botSettingsFile = new FileInputStream("settings");
			SETTINGS.load(botSettingsFile);
			botSettingsFile.close();
		} catch (IOException e) {
			LOGGER.fatal("Cannot Find Settings File!");
			return;
		}

		// Loads Bot
		try {
			final Properties botProperties = new Properties();
			final FileInputStream botPropertiesFile = new FileInputStream("token");
			botProperties.load(botPropertiesFile);
			botPropertiesFile.close();
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
				LOGGER.fatal("Please Place the Correct Token Inside of the Bot Properties File");
				return;
			}
		} catch (IOException e) {
			LOGGER.fatal("Cannot Find Token File!");
			return;
		}

		Music.init();
	}
}
