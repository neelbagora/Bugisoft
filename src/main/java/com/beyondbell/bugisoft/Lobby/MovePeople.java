package com.beyondbell.bugisoft.Lobby;

import com.beyondbell.bugisoft.Bot;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
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

	public MovePeople(final UserUpdateGameEvent event) {
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

}
