package com.beyondbell.bugisoft.Commands.Lobby;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.util.ArrayList;
import java.util.List;

public class MovePeople {
    public MovePeople(MessageReceivedEvent event, String lobbyParam) {
        String defaultLobby;
        for(int i = 0; i < event.getGuild().getVoiceChannels().size(); i++) {
            if(event.getGuild().getVoiceChannels().get(i).getName().equals(lobbyParam)) {
                defaultLobby = lobbyParam;
                break;
            }
        }
        ArrayList<String> gameNames = new ArrayList<String>();
        List<net.dv8tion.jda.core.entities.Member> members = event.getGuild().getMembers();
        for (int i = 0; i < event.getGuild().getMembers().size(); i++) {
            gameNames.set(i,members.get(i).getGame().toString());
        }
    }
}
