package com.beyondbell.bugisoft.Utilities.Converters;

public class StringToInteger {
	public static Integer convert(String string) throws Throwable {
		int integer = 0;

		for (int digitPlace = string.length() - 1; digitPlace >= 0; digitPlace--) {
			switch (string.charAt(string.length() - 1 - digitPlace)) {
				case '1':
					integer += 1 * Math.pow(10, digitPlace);
					break;
				case '2':
					integer += 2 * Math.pow(10, digitPlace);
					break;
				case '3':
					integer += 3 * Math.pow(10, digitPlace);
					break;
				case '4':
					integer += 4 * Math.pow(10, digitPlace);
					break;
				case '5':
					integer += 5 * Math.pow(10, digitPlace);
					break;
				case '6':
					integer += 6 * Math.pow(10, digitPlace);
					break;
				case '7':
					integer += 7 * Math.pow(10, digitPlace);
					break;
				case '8':
					integer += 8 * Math.pow(10, digitPlace);
					break;
				case '9':
					integer += 9 * Math.pow(10, digitPlace);
					break;
				case '0':
					break;
				default:
					throw new Throwable();
			}
		}
		return integer;
	}
}
