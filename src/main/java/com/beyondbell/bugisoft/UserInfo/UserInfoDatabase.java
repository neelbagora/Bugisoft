package com.beyondbell.bugisoft.UserInfo;

import com.beyondbell.bugisoft.Bot;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

public class UserInfoDatabase {
	private final static ArrayList<UserInfo> users = new ArrayList<>();

	public static UserInfo findUser(User user) {
		for (UserInfo userEntry : users) {
			if (userEntry.getID().equals(user.getId())) {
				return userEntry;
			}
		}
		registerUser(user);
		return users.get(users.size() - 1);
	}

	private static void registerUser(User user) {
		users.add(new UserInfo(user));
		clearOldUsers();
	}

	private static void clearOldUsers() {
		final int MAX_USER_CACHE = Integer.parseInt(Bot.SETTINGS.getProperty("USERS_MAX_LENGTH"));
		if (MAX_USER_CACHE == 0) {
			while (users.size() > 1) {
				users.remove(0);
			}
		}
		if (MAX_USER_CACHE > 0) {
			while (users.size() > MAX_USER_CACHE) {
				users.remove(0);
			}
		}
	}
}
