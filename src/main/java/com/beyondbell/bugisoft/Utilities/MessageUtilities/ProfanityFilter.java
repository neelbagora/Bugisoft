package com.beyondbell.bugisoft.Utilities.MessageUtilities;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;

public class ProfanityFilter {
    private final String message;


    public ProfanityFilter(final GuildMessageReceivedEvent event)  {
        message = event.getMessage().getContentRaw().toLowerCase();

        FileInputStream BadWords = null;
        try {
            BadWords = new FileInputStream("BadWords");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(BadWords));

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

    }
}
