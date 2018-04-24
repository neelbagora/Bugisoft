package com.beyondbell.bugisoft.Lobby;
import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.channel.server.voice.ServerVoiceChannelMemberJoinListener;

import java.lang.reflect.Member;
import java.util.Collection;
import java.util.ArrayList;

public class MovePeople {
    public void onMessageCreate(MessageCreateEvent event) {
        final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());
        ServerVoiceChannel lobby = null;
        ArrayList<User> gameNames = new ArrayList<User>();

        //Assign Lobby to be used
        if(parameters[0].equals("!") && parameters[1].equals("setLobby")) {
            lobby = event.getServer().get().getVoiceChannelsByName(parameters[3]).get(0);
            if(event.getServer().get().getVoiceChannelsByName(lobby.getName()) == null) {
                event.getChannel().sendMessage("Please enter valid voice channel name :)");

            }
        }

        User[] users = new User[event.getServer().get().getMemberCount()];
        users = event.getServer().get().getMembers().toArray(users);

        for(User member : users) {
            if(member.isConnected(lobby)) {
                gameNames.add(member);
            }
        }


        for(User guy : gameNames) {
            int count = 0;
            for(int i = 0; i < event.getServer().get().getVoiceChannels().size(); i++) {
                if(event.getServer().get().getVoiceChannels().get(i).getName() != guy.getActivity().get().getName()) {
                    count += 1;
                    if(count == event.getServer().get().getVoiceChannels().size()) {
                        event.getServer().get().createVoiceChannelBuilder().setName(guy.getActivity().get().getName()).create();
                        //TODO put user in the newly created channel
                        count = 0;
                    }
                } else {
                    //TODO put user in found channel
                    event.getApi();
                    count = 0;
                }
            }

        }

    }
}
