package com.beyondbell.bugisoft.Report;


import com.beyondbell.bugisoft.Logger.LoggerDatabase;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.util.ArrayList;

import com.beyondbell.bugisoft.Logger.LoggerDatabase.*;


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

            //current channel;
            TextChannel channel = event.getChannel();

            //arranges messages into messages arrayList
            int count = 1;
            Message[] messages = LoggerDatabase.getMessages(event, count);

            ArrayList<Message> storage = new ArrayList<>();


            for(int i = 0; i < messages.length; i++) {
                if(messages[i] == null) {
                    break;
                }else if(storage.size() == amount) {
                    break;
                }
                 else if(messages[i].getAuthor().getId() != id) {
                    count+=1;
                    messages = LoggerDatabase.getMessages(event,count);
                } else if(storage.size() != amount){
                    storage.add(messages[i]);
                }
            }

            EmbedBuilder embed = new EmbedBuilder();

                embed.setTitle(event.getServer().get().getDisplayName((event.getApi().getYourself()))
                        + "Messages from: " + display);

                //for loop to add fields to embed
                for(int i = amount; i > 0; i--) {
                    embed.addField("", storage.get(i).getContent());


                }

                embed.setAuthor(event.getMessage().getAuthor().getName());
            event.getChannel().sendMessage(embed);
            event.getMessage().delete(event.getMessage().getIdAsString());
        }


    }
}
