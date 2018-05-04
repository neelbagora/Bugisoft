package com.beyondbell.bugisoft.Commands.Lobby;
import net.dv8tion.jda.core.entities.Member;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import java.util.ArrayList;
import net.dv8tion.jda.core.managers.GuildController;

public class MovePeople {
    
    private boolean state = true;
    private GuildVoiceJoinEvent message = null;
    private String lobby = null;
    private GuildController server;

    //returns lobby, utilized in voiceChannelVoiceEvent
    public String getLOBBY() {
        return lobby;
    }

    //Used for setting if movePeople should be on or off
    public MovePeople(boolean action) {
        state = action;
        if(action) {
            server = new GuildController(message.getGuild());
        }
    }

    //Used when VoiceChannelJoinEvent pushes event to this class
    public MovePeople(GuildVoiceJoinEvent event) {
        message = event;
        if(state) {
            server = new GuildController(message.getGuild());
                String defaultLobby = lobby;
                for(int i = 0; i < server.getGuild().getVoiceChannels().size(); i++) {
                    if(!server.getGuild().getVoiceChannels().get(i).getName().equals(message.getMember().getGame().toString())) {
                        server.createVoiceChannel(message.getMember().getGame().toString());
                    }
                }
        }
    }

    //used for setting default lobby
    public MovePeople(String lobbyParam) {
        this.lobby = lobbyParam;
        if(state) {
            server = new GuildController(message.getGuild());
        }
    }

}

