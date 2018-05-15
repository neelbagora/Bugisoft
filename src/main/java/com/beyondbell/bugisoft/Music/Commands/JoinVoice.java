package com.beyondbell.bugisoft.Music.Commands;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

public class JoinVoice {
	private final Message message;

	public JoinVoice(GuildMessageReceivedEvent event) {
		TextChannel channel = event.getChannel();

		if(!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
			// The bot does not have permission to join any voice channel. Don't forget the .queue()!
			message = channel.sendMessage("I do not have permissions to join a voice channel!").complete();
			Thread thread = new Thread(this::deleteMessage);
			thread.setDaemon(true);
			thread.start();
			return;
		}
		// Creates a variable equal to the channel that the user is in.
		VoiceChannel connectedChannel = event.getMember().getVoiceState().getChannel();
		// Checks if they are in a channel -- not being in a channel means that the variable = null.
		if(connectedChannel == null) {
			// Don't forget to .queue()!
			message = channel.sendMessage("You are not connected to a voice channel!").complete();
			Thread thread = new Thread(this::deleteMessage);
			thread.setDaemon(true);
			thread.start();
			return;
		}
		// Gets the audio manager.
		AudioManager audioManager = event.getGuild().getAudioManager();
		// When somebody really needs to chill.
		if(audioManager.isAttemptingToConnect()) {
			message = channel.sendMessage("The bot is already trying to connect! Enter the chill zone!").complete();
			Thread thread = new Thread(this::deleteMessage);
			thread.setDaemon(true);
			thread.start();
			return;
		}
		// Connects to the channel.
		audioManager.openAudioConnection(connectedChannel);
		// Obviously people do not notice someone/something connecting.
		message = channel.sendMessage("Connected to the voice channel!").complete();

		Thread thread = new Thread(this::deleteMessage);
		thread.setDaemon(true);
		thread.start();
	}

	private void deleteMessage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ignored) {

		}
		message.delete().queue();
	}
}
