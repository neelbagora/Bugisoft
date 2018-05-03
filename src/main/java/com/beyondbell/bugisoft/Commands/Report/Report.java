
package com.beyondbell.bugisoft.Commands.Report;


import com.beyondbell.bugisoft.Logger.LoggerDatabase;
import net.dv8tion.jda.core.entities.Message;
import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.util.ArrayList;


public class Report {

    public void onMessageCreate(MessageReceivedEvent event) {
        final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getContentDisplay());

        if(parameters[0].equals("!") && parameters[1].equals("report")) {
            long id;
            String display;
            if(parameters[2] != null) {
                id = Long.parseLong(parameters[2]);
                display = event.getGuild().getMemberById(id).getNickname();
                if(parameters[3] != null) {

                } else {
                    event.getTextChannel().sendMessage("Follow format: !report nickname numberOfMessages");
                }
            } else {
                event.getTextChannel().sendMessage("Follow format: !report nickname numberOfMessages");
            }
            //planning on using command to get amount user messages and sending them to chat


            //identifies Member


            //number of messages
            int amount = Integer.parseInt(parameters[3]);

            //arranges messages into messages Array
            int count = 100;
            Message[] messages = LoggerDatabase.getMessages(event, count);

            ArrayList<Message> storage = new ArrayList<Message>();


            for(int i = 0; i < messages.length; i++) {
                if(amount != storage.size()) {
                    if(messages[i] == null) {
                        break;
                    }
                    else if(messages[i].getAuthor().getIdLong() == id) {
                        storage.add(messages[i]);
                    }
                    if(i == count) {
                        count += 100;
                        messages = LoggerDatabase.getMessages(event, count);
                    }
                } else {
                    break;
                }
            }

            EmbedBuilder embed = new EmbedBuilder();

                embed.setTitle("Messages from: " + display);

                //for loop to add fields to embed
                for(int i = storage.size() - 1; i > -1; i--) {
                    embed.addField("", storage.get(i).getCreationTime().toString() + ": " + storage.get(i).getContentDisplay(), true);

                }
                embed.setAuthor(event.getMessage().getAuthor().getName());
            event.getChannel().sendMessage(embed.build());
            event.getMessage().delete();
        }




    }


}

