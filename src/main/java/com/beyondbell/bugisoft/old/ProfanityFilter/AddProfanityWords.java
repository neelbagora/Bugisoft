package com.beyondbell.bugisoft.old.ProfanityFilter;

import java.io.FileWriter;
import java.io.IOException;

public class AddProfanityWords {
	public AddProfanityWords(String word) {
		try {
			new FileWriter("badwords", true)
					.append("\n")
					.append(word)
					.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
