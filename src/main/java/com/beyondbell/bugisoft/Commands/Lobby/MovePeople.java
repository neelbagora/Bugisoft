
package com.beyondbell.bugisoft.Commands.Lobby;
import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class MovePeople {
    public MovePeople(MessageReceivedEvent event, VoiceChannel lobbyParam) {
        final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getContentDisplay());
        VoiceChannel lobby = lobbyParam;
        ArrayList<Member> gameNames = new ArrayList<Member>();
        ArrayList<Member> members = new ArrayList<Member>();

        for(int i = 0; i < event.getGuild().getMembers().size(); i++) {

        }
    }
}
