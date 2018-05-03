package com.beyondbell.bugisoft.Commands.Lobby;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.util.ArrayList;
import net.dv8tion.jda.core.managers.GuildController;

public class MovePeople {
    boolean state = true;
    private MessageReceivedEvent MESSAGE = null;
    private String LOBBY = null;
    public MovePeople(boolean action) {
        state = action;
    }

    public MovePeople(MessageReceivedEvent event, String lobbyParam) {
        MESSAGE = event;
        this.LOBBY = lobbyParam;
    }

    public MovePeople() {
        if(state) {
            String defaultLobby = LOBBY;

            ArrayList<String> gameNames = new ArrayList<>();
            ArrayList<Member> members = new ArrayList<>();
            for(int i = 0; i < MESSAGE.getGuild().getMembers().size(); i++) {
                if(MESSAGE.getGuild().getMembers().get(i).getVoiceState().inVoiceChannel()) {
                    members.add(MESSAGE.getGuild().getMembers().get(i));
                }
            }

            for (int i = 0; i < members.size(); i++) {
                gameNames.set(i,members.get(i).getGame().toString());
            }
            GuildController server = new GuildController(MESSAGE.getGuild());

            for(int i = 0; i < gameNames.size(); i++) {
                for(int j = 0; j < server.getGuild().getVoiceChannels().size(); j++) {
                    if(!(server.getGuild().getVoiceChannels().get(j).getName().equals(gameNames.get(i)))) {
                        server.createVoiceChannel(gameNames.get(i));
                    }
                }
            }

            for(Member people : members) {
                for(int i = 0; i < server.getGuild().getVoiceChannels().size(); i++) {
                    if(people.getGame().toString().equals(server.getGuild().getVoiceChannels().get(i))) {
                        server.moveVoiceMember(people, server.getGuild().getVoiceChannels().get(i));
                    }
                }
            }

            for(int i = 0; i < server.getGuild().getVoiceChannels().size(); i++) {
                if(server.getGuild().getVoiceChannels().get(i).getMembers().size() == 0 && !server.getGuild().getVoiceChannels().get(i).equals(defaultLobby)) {
                    server.getGuild().getVoiceChannels().get(i).delete();
                }
            }
        }

    }
    }
}
