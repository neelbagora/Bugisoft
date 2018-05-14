package com.beyondbell.bugisoft.Utilities.TextFormatters;

public class IdFormatter {
    public static String fixId(String input) {
        return input.substring(2,input.length() - 1);
    }
}
