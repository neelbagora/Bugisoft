package com.beyondbell.bugisoft.old.UserInfo;

import com.beyondbell.bugisoft.old.UserInfo.Tags.Tag;
import net.dv8tion.jda.core.entities.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

final class UserInfo {
	private volatile Properties userProperties;
	private volatile ArrayList<Tag> tags;

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

	final Properties getUserProperties() {
		return userProperties;
	}

	final String getID() {
		return userProperties.getProperty("id");
	}

	final ArrayList<Tag> getTags() {
		return tags;
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
