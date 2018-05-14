package com.beyondbell.bugisoft.UserInfo;

import net.dv8tion.jda.core.entities.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UserInfo {
	private final Properties userProperties = new Properties();

	UserInfo(User user) {
		FileInputStream userPropertiesFileIn;
		try { // Attempts to Reach the File
			userPropertiesFileIn = new FileInputStream("users/" + user.getId());
			userProperties.load(userPropertiesFileIn);
		} catch (FileNotFoundException e) {
			Properties defaultUserProperties = new Properties();
			try {
				FileInputStream defaultUserPropertiesFile = new FileInputStream("defaultUserProperties");
				defaultUserProperties.load(defaultUserPropertiesFile);
				defaultUserPropertiesFile.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			defaultUserProperties.setProperty("id", user.getId());
			defaultUserProperties.setProperty("username", user.getName() + user.getDiscriminator());

			try {
				FileOutputStream userPropertiesFileOut = new FileOutputStream("users/" + user.getId());
				defaultUserProperties.store(userPropertiesFileOut, user.getName());
				userPropertiesFileOut.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				userPropertiesFileIn = new FileInputStream("users/" + user.getId());
				userProperties.load(userPropertiesFileIn);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String getUsername() {
		return userProperties.getProperty("username");
	}

	String getLevel() {
		return userProperties.getProperty("level");
	}

	String getCash() {
		return userProperties.getProperty("cash");
	}

	String getWins() {
		return userProperties.getProperty("wins");
	}

	String getLosses() {
		return userProperties.getProperty("losses");
	}

	String getID() {
		return userProperties.getProperty("id");
	}

	public boolean getGameShouldMove() {
		return userProperties.getProperty("gameMoveEnabled").equals("true");
	}
}
