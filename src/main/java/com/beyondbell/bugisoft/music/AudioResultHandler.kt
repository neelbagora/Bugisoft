package com.beyondbell.bugisoft.music

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

class AudioResultHandler : AudioLoadResultHandler {
    override fun loadFailed(exception: FriendlyException?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun trackLoaded(track: AudioTrack?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun noMatches() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playlistLoaded(playlist: AudioPlaylist?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}