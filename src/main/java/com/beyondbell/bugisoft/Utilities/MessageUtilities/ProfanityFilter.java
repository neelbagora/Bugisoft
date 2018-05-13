package com.beyondbell.bugisoft.Utilities.MessageUtilities;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.io.*;

public class ProfanityFilter {
    private final String message;


    public ProfanityFilter(final MessageReceivedEvent event)  {
        message = event.getMessage().toString().toLowerCase();

        FileInputStream BadWords = null;
        try {
            BadWords = new FileInputStream("BadWords");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(BadWords));

        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if(message.contains(line)) {
                    event.getMessage().delete().queue();
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
