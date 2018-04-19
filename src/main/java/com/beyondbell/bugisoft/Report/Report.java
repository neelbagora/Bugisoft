package com.beyondbell.bugisoft.Report;


import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;
import java.util.Optional;

public class Report implements MessageCreateListener {

    public void onMessageCreate(MessageCreateEvent event) {
        ArrayList<String> parameters = InputFormatter.stringToParameters(event);


        if(parameters.get(0).equals("!") && parameters.get(1).equals("report")) {
            Optional<Server> x = event.getServer();
            //planning on using command to get amount user messages and sending them to chat
            long id = Long.parseLong(parameters.get(2));
            int amount = Integer.parseInt(parameters.get(3));
            TextChannel channel = event.getChannel();
            //arranges messages into messages arrayList
            ArrayList<String> messages = new ArrayList<String>(amount);
            User user = ;
            for(int i = 0; i < amount; i++) {
                String x = channel.getMessageById(id).toString();
                if(x == null) {
                    break;
                } else {
                    messages.add(x);
                }
            }

            EmbedBuilder embed = new EmbedBuilder();

                embed.setTitle("Messages from: ")
                embed.addField("", );
                //for loop to add fields to embed
                for(byte i = 0; i < amount; i++) {

                }
        }
    }
}
