package com.beyondbell.bugisoft.UserInfo;

import org.javacord.api.entity.message.MessageAuthor;

import java.util.ArrayList;

class UserInfoDatabase {
	private static ArrayList<UserInfo> users = new ArrayList<>();

	static void registerUser(MessageAuthor user) {
		users.add(new UserInfo(user));
	}

	static UserInfo findUser(MessageAuthor author) {
		for (UserInfo user : users) {
			if (user.getID().equals(author.getIdAsString())) {
				return user;
			}
		}
		registerUser(author);
		return users.get(users.size() - 1);
	}
}
