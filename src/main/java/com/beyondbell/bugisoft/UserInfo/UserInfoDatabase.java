package com.beyondbell.bugisoft.UserInfo;

import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;

class UserInfoDatabase {
	private final static ArrayList<UserInfo> users = new ArrayList<>();

	private static void registerUser(User user) {
		users.add(new UserInfo(user));
	}

	static UserInfo findUser(User user) {
		for (UserInfo userEntry : users) {
			if (userEntry.getID().equals(user.getId())) {
				return userEntry;
			}
		}
		registerUser(user);
		return users.get(users.size() - 1);
	}
}
