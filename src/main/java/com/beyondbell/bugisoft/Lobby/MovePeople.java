package com.beyondbell.bugisoft.Lobby;

import com.beyondbell.bugisoft.Bot;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;
import net.dv8tion.jda.core.managers.GuildController;

public class MovePeople {
	public MovePeople(final GuildVoiceJoinEvent event) {
		if (event.getMember().getGame() == null) {
			return;
		}

		final Category category;
		if (event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("gameChannelsCategory"), true).size() != 0) {
			category = event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("gameChannelsCategory"), true).get(0);
		} else {
			Bot.LOGGER.warn("Could Not Find Category With the Name of {" + Bot.SETTINGS.getProperty("gameChannelsCategory") + "}!");
			return;
		}

		// Checks to See if Game Channel Already Exists
		for (VoiceChannel channel : event.getGuild().getCategoryById(category.getId()).getVoiceChannels()) {
			if (channel.getName().equals(event.getMember().getGame().getName())) {
				new GuildController(event.getGuild()).moveVoiceMember(event.getMember(), channel).queue();
				return;
			}
		}

		// Otherwise Creates the Channel
		final Channel channel = new GuildController(event.getGuild())
				.createVoiceChannel(event.getMember().getGame().getName())
				.setParent(category)
				.setBitrate(96000)
				.complete();

		new GuildController(event.getGuild()).moveVoiceMember(event.getMember(), (VoiceChannel) channel).queue();
	}

	public MovePeople(UserUpdateGameEvent event) {
		GuildController controller = new GuildController(event.getGuild());
		//category under where the channel must be created
		if (event.getGuild().getCategoriesByName(category, true).size() > 0) {
			category1 = event.getGuild().getCategoriesByName(category, true).get(0);
		} else {
			category1 = null;
		}


		if (event.getNewGame() == null) {
			VoiceChannel x = event.getMember().getVoiceState().getChannel();
			if (event.getMember().getVoiceState().inVoiceChannel()) {
				controller.moveVoiceMember(event.getMember(), event.getGuild().getVoiceChannelsByName("lobby", true).get(0)).queue();
			} else {
				return;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			x.delete().queue();
			return;
		}

		//if user is connected
		if (event.getMember().getVoiceState().inVoiceChannel()) {
			//set category
			for (VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
				if (voiceChannel.getName().equals(event.getNewGame().getName())) {
					controller.moveVoiceMember(event.getMember(), voiceChannel).queue();
					break;
				}
			}

			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			new GuildController(event.getGuild()).createVoiceChannel(event.getNewGame().getName()).setParent(category1).queue();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
				if (voiceChannel.getName().equals(event.getNewGame().getName())) {
					controller.moveVoiceMember(event.getMember(), voiceChannel).queue();
					return;
				}
			}
		}
	}
}
