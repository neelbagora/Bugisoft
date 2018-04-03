package com.beyondbell.bugisoft.TextFormatters;

import org.javacord.api.event.message.MessageCreateEvent;

import java.util.ArrayList;

public class InputFormatter {
	public static ArrayList<String> stringToParameters(MessageCreateEvent event) {
		String message = event.getMessage().getContent();

		ArrayList<String> parameters = new ArrayList<>();

		if (event.getMessage().getAuthor().isYourself() || message.length() == 0) {
			parameters.add("null");
			return parameters;
		}

		// Checks for Command Prefix
		switch (message.charAt(0)) {
			case '!':
				parameters.add("!");
				break;
			case '>':
				parameters.add(">");
				break;
			case ';':
				parameters.add(";");
				break;
			case '^':
				parameters.add("^");
				break;
			default:
				parameters.add("null");
				return parameters;
		}

		// Builds the Other Parameters
		StringBuilder currentParameter = new StringBuilder();
		for (int i = 1; i < message.length(); i++) {
			if (message.charAt(i) != ' ') {
				currentParameter.append(message.charAt(i));
			} else {
				parameters.add(currentParameter.toString());
				currentParameter = new StringBuilder();
			}
		}
		parameters.add(currentParameter.toString());

		return parameters;
	}
}
