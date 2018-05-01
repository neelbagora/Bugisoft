package com.beyondbell.bugisoft.Utilities.TextFormatters;

import com.beyondbell.bugisoft.Utilities.Converters.ArrayAndArrayListConverters;

import java.util.ArrayList;

public class InputFormatter {
	public static String[] stringToParameters(String message) {
		ArrayList<String> parameters = new ArrayList<>();

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

		if (parameters.size() > 1) {
			// Removes Whitespace at the End
			while (parameters.get(parameters.size() - 1).equals("")) {
				parameters.remove(parameters.size() - 1);
			}

		}

		return ArrayAndArrayListConverters.arrayListToArrayConverter(parameters);
	}
}
