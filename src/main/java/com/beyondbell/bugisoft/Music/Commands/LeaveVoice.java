package com.beyondbell.bugisoft.Music.Commands;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class LeaveVoice {
	private final Message message;

	public LeaveVoice(GuildMessageReceivedEvent event) {
		TextChannel channel = (TextChannel) event.getChannel();
		// Gets the channel in which the bot is currently connected.
		VoiceChannel connectedChannel = event.getGuild().getSelfMember().getVoiceState().getChannel();
		// Checks if the bot is connected to a voice channel.
		if(connectedChannel == null) {
			// Get slightly fed up at the user.
			message = channel.sendMessage("I am not connected to a voice channel!").complete();
			Thread thread = new Thread(this::deleteMessage);
			thread.setDaemon(true);
			thread.start();
			return;
		}
		// Disconnect from the channel.
		event.getGuild().getAudioManager().closeAudioConnection();
		// Notify the user.
		message = channel.sendMessage("Disconnected from the voice channel!").complete();

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
