package com.beyondbell.bugisoft.UserInfo;

import net.dv8tion.jda.core.entities.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UserInfo {
	private volatile Properties userProperties;

	UserInfo(User user) {
		userProperties = new Properties();
		try {
			final FileInputStream userPropertiesFileIn = new FileInputStream("users/" + user.getId());
			userProperties.load(userPropertiesFileIn);
		} catch (FileNotFoundException e) {
			userProperties.setProperty("id", user.getId());
			userProperties.setProperty("username", user.getName() + user.getDiscriminator());
			saveProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Properties getUserProperties() {
		return userProperties;
	}

	String getUsername() {
		return userProperties.getProperty("username");
	}

	String getID() {
		return userProperties.getProperty("id");
	}

	public boolean getGameShouldMove() {
		if (userProperties.getProperty("gameShouldMove") != null) {
			return userProperties.getProperty("gameShouldMove").equals("true");
		} else {
			copyProperty("gameShouldMove");
			saveProperties();
			return userProperties.getProperty("gameShouldMove").equals("true");
		}
	}

	private void copyProperty(final String property) {
		final Properties defaultUserProperties = new Properties();
		try {
			FileInputStream defaultUserPropertiesFile = new FileInputStream("defaultUserProperties");
			defaultUserProperties.load(defaultUserPropertiesFile);
			defaultUserPropertiesFile.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		userProperties.setProperty(property, defaultUserProperties.getProperty(property));

		saveProperties();
	}

	private void saveProperties() {
		try {
			FileOutputStream userPropertiesFileOut = new FileOutputStream("users/" + userProperties.getProperty("id"));
			userProperties.store(userPropertiesFileOut, userProperties.getProperty("username"));
			userPropertiesFileOut.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
