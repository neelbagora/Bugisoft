package com.beyondbell.bugisoft;

import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;

/**
 * This Place Just Has Any Command Currently Being Tested
 */
public class TestCommand implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		ArrayList<String> p = InputFormatter.stringToParameters(event);
		if (p.get(0).equals("null")) {
			return;
		}

		for (String s : p) {
			System.out.print(s + "\t");
		}
		System.out.println();
	}
}
