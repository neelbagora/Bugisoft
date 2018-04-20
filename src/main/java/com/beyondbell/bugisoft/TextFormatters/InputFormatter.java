package com.beyondbell.bugisoft.TextFormatters;

import java.util.ArrayList;

public class InputFormatter {

	public static String[] stringToParameters(String message) {
		ArrayList<String> parameters = new ArrayList<>();
		String[] finalizedParameters = new String[0];

		if (message.length() == 0 || message.length() == 1) {
			parameters.add("null");
			return parameters.toArray(finalizedParameters);
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
				return parameters.toArray(finalizedParameters);
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

		while (parameters.get(parameters.size() - 1).equals("")) {
			parameters.remove(parameters.size() - 1);
		}

		return parameters.toArray(finalizedParameters);
	}
}
