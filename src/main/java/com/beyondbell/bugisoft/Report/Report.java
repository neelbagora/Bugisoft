package com.beyondbell.bugisoft.Report;


import com.beyondbell.bugisoft.Logger.LoggerDatabase;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.entity.user.User;
import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.util.ArrayList;


public class Report implements MessageCreateListener {

    public void onMessageCreate(MessageCreateEvent event) {
        final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());

        if(parameters[0].equals("!") && parameters[1].equals("report")) {

            //planning on using command to get amount user messages and sending them to chat
            long id = Long.parseLong(parameters[2]);

            //identifies User
            User member = event.getServer().get().getMemberById(id).get();
            String display = event.getServer().get().getDisplayName(member);

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
                    else if(messages[i].getAuthor().getId() == id) {
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
                for(int i = amount - 1; i > -1; i--) {
                    embed.addField("", storage.get(i).getCreationTimestamp().toString() + ": " + storage.get(i).getContent());
                }
                embed.setAuthor(event.getMessage().getAuthor().getName());
            event.getChannel().sendMessage(embed);
            event.getMessage().delete(event.getMessage().getIdAsString());
        }


    }
}
