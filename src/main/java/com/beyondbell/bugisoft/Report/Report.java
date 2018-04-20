package com.beyondbell.bugisoft.Report;


import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.util.ArrayList;


public class Report implements MessageCreateListener {

    public void onMessageCreate(MessageCreateEvent event) {
        ArrayList<String> parameters = InputFormatter.stringToParameters(event);

        if(parameters.get(0).equals("!") && parameters.get(1).equals("report")) {

            //planning on using command to get amount user messages and sending them to chat
            long id = Long.parseLong(parameters.get(2));

            //identifies User
            User member = event.getServer().get().getMemberById(id).get();
            String display = event.getServer().get().getDisplayName(member);

            //number of messages
            int amount = Integer.parseInt(parameters.get(3));

            //current channel;
            TextChannel channel = event.getChannel();

            //arranges messages into messages arrayList
            int count = 1;
            Message messages = (Message) event.getChannel().getMessagesBefore(count, event.getMessageId());
            ArrayList<Message> storage = new ArrayList<>();


            for(int i = 0; i < Integer.MAX_VALUE; i++) {
                if(messages == null) {
                    break;
                }else if(storage.size() == amount) {
                    break;
                }
                 else if(messages.getAuthor().getId() != id) {
                    count+=1;
                    messages = (Message) event.getChannel().getMessagesBefore(count, event.getMessage());
                } else if(storage.size() != amount){
                    storage.add(messages);
                }
            }

            EmbedBuilder embed = new EmbedBuilder();

                embed.setTitle(event.getServer().get().getDisplayName((event.getApi().getYourself()))
                        + "Messages from: " + display);

                //for loop to add fields to embed
                for(int i = amount; i > 0; i--) {
                    embed.addField("", storage.get(i).getContent());
                    //embed.addField("Time: ", event.getMessage().get)

                }

                embed.setAuthor(event.getMessage().getAuthor().getName());
            event.getChannel().sendMessage(embed);
            event.getMessage().delete(event.getMessage().getIdAsString());
        }


    }
}
