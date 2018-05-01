package com.beyondbell.bugisoft.Music.Commands;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

public class JoinVoice {
	public JoinVoice(MessageReceivedEvent event) {
		TextChannel channel = event.getTextChannel();

		if(!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
			// The bot does not have permission to join any voice channel. Don't forget the .queue()!
			channel.sendMessage("I do not have permissions to join a voice channel!").queue();
			return;
		}
		// Creates a variable equal to the channel that the user is in.
		VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
		// Checks if they are in a channel -- not being in a channel means that the variable = null.
		if(connectedChannel == null) {
			// Don't forget to .queue()!
			channel.sendMessage("You are not connected to a voice channel!").queue();
			return;
		}
		// Gets the audio manager.
		AudioManager audioManager = event.getGuild().getAudioManager();
		// When somebody really needs to chill.
		if(audioManager.isAttemptingToConnect()) {
			channel.sendMessage("The bot is already trying to connect! Enter the chill zone!").queue();
			return;
		}
		// Connects to the channel.
		audioManager.openAudioConnection(connectedChannel);
		// Obviously people do not notice someone/something connecting.
		channel.sendMessage("Connected to the voice channel!").queue();
	}
}
