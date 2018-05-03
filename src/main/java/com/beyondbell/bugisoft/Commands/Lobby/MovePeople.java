
package com.beyondbell.bugisoft.Commands.Lobby;
import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.channel.server.voice.ServerVoiceChannelMemberJoinListener;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class MovePeople {
    public void onMessageCreate(MessageReceivedEvent event) {
        final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());
        VoiceChannel lobby = null;
        ArrayList<Member> gameNames = new ArrayList<Member>();

        //Assign Lobby to be used
        if(parameters[0].equals("!") && parameters[1].equals("setLobby")) {
            lobby = event.getGuild().getVoiceChannelsByName();
            if(event.getServer().get().getVoiceChannelsByName(lobby.getName()) == null) {
                event.getChannel().sendMessage("Please enter valid voice channel name :)");

            }
        }

        List<net.dv8tion.jda.core.entities.Member> users = event.getGuild().getMembers();

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

                    count = 0;
                }
            }

        }

    }
}
