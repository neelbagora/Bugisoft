
package com.beyondbell.bugisoft.Commands.Report;


import com.beyondbell.bugisoft.Logger.LoggerDatabase;
import net.dv8tion.jda.core.entities.Message;
import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.util.ArrayList;


public class Report {

    private final MessageReceivedEvent event;
    private final int number;


    public Report (MessageReceivedEvent event, int numberReports) {
        this.event = event;
        number = numberReports;
        final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getContentDisplay());

        if(parameters[0].equals("!") && parameters[1].equals("report")) {
            //gets id
            long id = Long.parseLong(parameters[2]);

            //identifies Member
            String display = event.getGuild().getMemberById(id).getNickname();

            //planning on using command to get amount user messages and sending them to chat

            //arranges messages into messages Array
            int count = 100;
            Message[] messages = LoggerDatabase.getMessages(event, count);

            ArrayList<Message> storage = new ArrayList<Message>();


            for(Message message : messages) {
                if(number > storage.size()) {
                    try {
                        storage.add(message);
                    } catch(NullPointerException error) {
                        break;
                    }
                } else {
                    break;
                }
            }

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle(storage.size() + " messages from: " + display);

            //for loop to add fields to embed
            for(int i = storage.size() - 1; i > -1; i--) {
                embed.addField("", storage.get(i).getCreationTime().toString() + ": " + storage.get(i).getContentDisplay(), true);

            }
            embed.setAuthor(event.getMessage().getAuthor().getName());
            event.getChannel().sendMessage(embed.build()).queue();
            event.getMessage().delete().queue();
        }




    }


}

