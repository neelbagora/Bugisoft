package com.beyondbell.bugisoft.music

import com.beyondbell.bugisoft.utilities.ErrorMessage
import com.beyondbell.bugisoft.utilities.sendErrorMessage
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat
import com.sedmelluq.discord.lavaplayer.format.OpusAudioDataFormat
import com.sedmelluq.discord.lavaplayer.format.StandardAudioDataFormats
import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.entities.VoiceChannel
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.managers.AudioManager

object Music {
    private val playerManager = DefaultAudioPlayerManager()
    private val audioPlayers = HashMap<Long, AudioManager>()

    init {
        playerManager.configuration.opusEncodingQuality = AudioConfiguration.OPUS_QUALITY_MAX
        playerManager.configuration.resamplingQuality = AudioConfiguration.ResamplingQuality.HIGH
        playerManager.configuration.outputFormat = StandardAudioDataFormats.DISCORD_OPUS

        AudioSourceManagers.registerRemoteSources(playerManager)
    }

    fun join(event: GuildMessageReceivedEvent) {
        // Finds User's Voice Channel
        val voiceChannel = getVoiceChannel(event.guild, event.author)
        if (voiceChannel == null) {
            sendErrorMessage(ErrorMessage.NotInVoice, event.channel)
            return
        }

        // Checks for Permission
        // TODO Check for Permission

        // Joins User's Voice Channel
        event.guild.audioManager.openAudioConnection(voiceChannel)

        val player = audioPlayers.getOrPut(event.guild.idLong, )
        event.guild.audioManager.sendingHandler = AudioPlayerSendHandler()


    }

    fun play(event: GuildMessageReceivedEvent) {

    }

    fun skip(event: GuildMessageReceivedEvent) {

    }

    fun stop(event: GuildMessageReceivedEvent) {

    }

    fun shuffle(event: GuildMessageReceivedEvent) {

    }

    fun toggleRepeatMode(event: GuildMessageReceivedEvent) {

    }

    private fun getVoiceChannel(guild: Guild, user: User): VoiceChannel? {
        for (voiceChannel in guild.voiceChannels) {
            for (voiceUser in voiceChannel.members) {
                if (user == voiceUser) {
                    return voiceChannel
                }
            }
        }
        return null
    }
}