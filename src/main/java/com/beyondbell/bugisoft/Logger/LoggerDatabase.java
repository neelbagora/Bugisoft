package com.beyondbell.bugisoft.Logger;

import org.javacord.api.entity.message.Message;
import org.javacord.api.event.Event;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoggerDatabase {
	static String fileName = "";
	public static Message[] getMessages(Event event, int numberOfMessages) throws IOException {
		String log = readFile(fileName,numberOfMessages);
		String[] messages = new String[numberOfMessages];
		int index = log.indexOf("/n"); 
		int initialIndex = 0;

		for(int i = 0; i < messages.length; i++) {
			if(index != -1) {
				messages[i] = log.substring(initialIndex, index);
				initialIndex += messages[i].length() + 2;
				index = log.indexOf("/n", index + 1);
			} else {
				messages[i] = log.substring(initialIndex, log.length());
				break;
			}
		}

		//need to convert strings to messages
		Message[] x = new Message[2];
		return x;
	}

	public static String readFile(String file, int number) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			for(int i = 0; i < number; i++) {
				if(line != null) {
					sb.append(line);
					sb.append("/n");
					line = br.readLine();
				}
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

}
