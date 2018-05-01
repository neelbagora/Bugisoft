package com.beyondbell.bugisoft.Music.Commands;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class LeaveVoice {
	public LeaveVoice(MessageReceivedEvent event) {
		TextChannel channel = (TextChannel) event.getChannel();
		// Gets the channel in which the bot is currently connected.
		VoiceChannel connectedChannel = event.getGuild().getSelfMember().getVoiceState().getChannel();
		// Checks if the bot is connected to a voice channel.
		if(connectedChannel == null) {
			// Get slightly fed up at the user.
			channel.sendMessage("I am not connected to a voice channel!").queue();
			return;
		}
		// Disconnect from the channel.
		event.getGuild().getAudioManager().closeAudioConnection();
		// Notify the user.
		channel.sendMessage("Disconnected from the voice channel!").queue();
	}
}
