package com.beyondbell.bugisoft.Commands.Lobby;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.managers.GuildController;

public class MovePeople {

    private MoveState state;
    private GuildVoiceJoinEvent message;
    private String lobby = null;
    private GuildController server;

    //on-off and boolean representation of code
    private enum MoveState {
        ON(true),
        OFF(false);

        boolean value;
        MoveState(boolean action) {
        	this.value = action;
        }
    }



    //returns lobby, utilized in voiceChannelVoiceEvent
    public String getLobby() {
        return lobby; //null case covered in VoiceChannelJoinEvent
    }

    //returns boolean representation of code state
    public boolean getStatus() {
        return state.value;
    }

    //default constructor used for checking instance variables, used only in 'VoiceChannelJoinEvent'
    public MovePeople() {}

    //Used for setting if movePeople should be on or off, syntax found in 'MessageReceivedHandler'
    public MovePeople(final boolean action) {
        if(action) {
        	state = MoveState.ON;
        } else {
        	state = MoveState.OFF;
        }
    }

    //Used when 'VoiceChannelJoinEvent' pushes event to this class
    public MovePeople(final GuildVoiceJoinEvent event) {
        message = event;
        server = new GuildController(message.getGuild());
        boolean check = true;
        for(int i = 0; i < server.getGuild().getVoiceChannels().size(); i++) {
            if(server.getGuild().getVoiceChannels().get(i).getName().equals(message.getMember().getGame().toString())) {
                    check = false;
                    break;
            }
        }

        if(check) {
	        server.createVoiceChannel(message.getMember().getGame().toString());
	        server.moveVoiceMember(message.getMember(), (VoiceChannel) server.getGuild().getVoiceChannelsByName(message.getMember().getGame().toString(),false));
        } else {
        	//safety measure
	        server.moveVoiceMember(message.getMember(), (VoiceChannel) server.getGuild().getVoiceChannelsByName(message.getMember().getGame().toString(),false));

        }


    }

    //used for setting default lobby, syntax found in 'MessageReceivedHandler'
    public MovePeople(String lobbyParam) {
        this.lobby = lobbyParam;
    }

}

