package com.beyondbell.bugisoft.Utilities.TextFormatters;

import java.util.ArrayList;

public final class ParametersFormatter {
	public static String[] stringToParameters(final String message) {
		final ArrayList<String> parameters = new ArrayList<>();

		// Adds the Prefix
		parameters.add(String.valueOf(message.charAt(0)));

		// Builds the Parameters
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

		// Removes Whitespace at the End
		if (parameters.size() > 1) {
			while (parameters.get(parameters.size() - 1).equals("")) {
				parameters.remove(parameters.size() - 1);
			}
		}

		return parameters.toArray(new String[0]);
	}
}
