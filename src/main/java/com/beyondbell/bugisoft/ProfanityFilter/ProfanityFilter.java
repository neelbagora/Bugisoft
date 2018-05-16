package com.beyondbell.bugisoft.ProfanityFilter;

import com.beyondbell.bugisoft.Bot;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class ProfanityFilter {
    public ProfanityFilter(final GuildMessageReceivedEvent event)  {
        final String message = event.getMessage().getContentRaw().toLowerCase();

	    try {
		    final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Bot.SETTINGS.getProperty("badWordsFile"))));
		    try {
			    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				    if(message.indexOf(line) != -1) {
					    int index = message.indexOf(line);
					    String badWord = message.substring(0,index + 1);
					    for(int i = 0; i < line.length() - 2; i++) {
						    badWord += "*";
					    }
					    badWord += message.substring(index + line.length() - 1, message.length());
					    event.getMessage().delete().queue();
					    event.getChannel().sendMessage("Flagged for profanity\nThe original message was \"" +
							    badWord + "\"").queue();

					    break;
				    }
			    }
			    reader.close();
		    } catch (IOException e) {
			    e.printStackTrace();
		    }
	    } catch (FileNotFoundException e) {
		    System.out.println("Please Verify Location/Permissions for the badWordsFile in the Settings File. " + Bot.SETTINGS.getProperty("badWordsFile") + " was Not Accessible.");
	    }
    }
}
