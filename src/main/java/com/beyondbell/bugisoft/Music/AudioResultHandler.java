package com.beyondbell.bugisoft.Music;

import com.beyondbell.bugisoft.Utilities.MessageUtilities.MessageBroadcaster;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class AudioResultHandler implements AudioLoadResultHandler {
	private final GuildMessageReceivedEvent event;
	private final GuildMusicManager guildMusicManager;
	private final String trackUrl;

	private AudioPlaylist playlist;
	private int count = 0;

	public AudioResultHandler(final GuildMessageReceivedEvent event, final String trackUrl) {
		this.event = event;
		this.trackUrl = trackUrl;

		this.guildMusicManager = Music.getGuildAudioPlayer(event.getGuild());
	}

	@Override
	public void trackLoaded(AudioTrack track) {
		JoinCurrentVoice();
		if (Music.getGuildAudioPlayer(event.getChannel().getGuild()).player.isPaused()) {
			Music.getGuildAudioPlayer(event.getChannel().getGuild()).player.setPaused(false);
		}

		new MessageBroadcaster(event.getChannel().sendMessage("Adding to queue " + track.getInfo().title).complete(), 5);

		Music.play(guildMusicManager, track);
	}

	@Override
	public void playlistLoaded(AudioPlaylist playlist) {
		JoinCurrentVoice();
		if (Music.getGuildAudioPlayer(event.getChannel().getGuild()).player.isPaused()) {
			Music.getGuildAudioPlayer(event.getChannel().getGuild()).player.setPaused(false);
		}

			this.playlist = playlist;

		final Thread t = new Thread(this::queueSongs);
		t.run();
	}

	@Override
	public void noMatches() {
		new MessageBroadcaster(event.getChannel().sendMessage("Nothing found by " + trackUrl).complete(), 5);
	}

	@Override
	public void loadFailed(FriendlyException exception) {
		new MessageBroadcaster(event.getChannel().sendMessage("Could not play: " + exception.getMessage()).complete(), 5);
	}

	private void queueSongs() {
		for (AudioTrack audioTrack : playlist.getTracks()) {
			if (audioTrack != null) {
				Music.play(guildMusicManager, audioTrack);
				count++;
			}
		}
		new MessageBroadcaster(event.getChannel().sendMessage("Added to Queue " + count + " songs of the playlist " + playlist.getName()).complete(), 5);
	}

	private void JoinCurrentVoice() {
		if (!event.getGuild().getAudioManager().isConnected() && !event.getGuild().getAudioManager().isAttemptingToConnect()) {
			for (VoiceChannel voiceChannel : event.getGuild().getVoiceChannels()) {
				for (Member member : voiceChannel.getMembers()) {
					if (event.getMember() == member) {
						event.getGuild().getAudioManager().openAudioConnection(voiceChannel);
						break;
					}
				}
			}
		}
	}
}
