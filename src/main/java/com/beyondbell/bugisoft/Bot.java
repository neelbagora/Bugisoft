package com.beyondbell.bugisoft;

import com.beyondbell.bugisoft.EventProcessor.EventCommandListener;
import com.beyondbell.bugisoft.EventProcessor.EventLoggerListener;
import com.beyondbell.bugisoft.EventProcessor.EventOtherListener;
import com.beyondbell.bugisoft.Music.Music;
import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class Bot {
	public static void main(String[] args) {
		// Loads Bot Properties
		Properties botProperties = new Properties();
		FileInputStream botPropertiesFile;
		try {
			botPropertiesFile = new FileInputStream("token");
			botProperties.load(botPropertiesFile);
			botPropertiesFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Initializes the Music Module
		Music.init();

		// Initializes the Bot
		try {
			new JDABuilder(AccountType.BOT)
					.setToken(String.valueOf(botProperties.getProperty("token")))
					.addEventListener(new EventCommandListener())
					.addEventListener(new EventLoggerListener())
					.addEventListener(new EventOtherListener())
					.setAudioSendFactory(new NativeAudioSendFactory())
					.buildBlocking();
		} catch (LoginException e) {
			System.out.println("Please Place the Correct Token Inside of the Bot Properties File");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}