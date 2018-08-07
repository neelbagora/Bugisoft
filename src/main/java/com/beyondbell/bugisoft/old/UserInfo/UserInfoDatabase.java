package com.beyondbell.bugisoft.old.UserInfo;

import com.beyondbell.bugisoft.old.Bot;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

final class UserInfoDatabase {
	private final static ArrayList<UserInfo> users = new ArrayList<>();

	private static volatile boolean clearing = false;

	static UserInfo findUser(User user) {
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
		if (!clearing) {
			clearing = true;
			clearOldUsers();
		}
	}

	private static synchronized void clearOldUsers() {
		if (Integer.parseInt(Bot.SETTINGS.getProperty("USERS_MAX_LENGTH")) >= 0) {
			for (int i = users.size() - 1; i >= 0 && users.size() > Integer.parseInt(Bot.SETTINGS.getProperty("USERS_MAX_LENGTH")); i--) {
				if (users.get(i).getTags().size() == 0) {
					users.remove(i);
				}
			}
		}
		clearing = false;
	}
}
