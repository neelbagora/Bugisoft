package com.beyondbell.bugisoft.music

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.YouTubeScopes
import com.google.api.services.youtube.model.SearchListResponse
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Arrays

object TrackFinder {
	private val youTube = getYouTubeService()

	fun getAudioTrackURLFromString(songContext: Array<String>): String {
		if (songContext[0].startsWith("http")) {
			return songContext[0]
		}

		val stringBuilder = StringBuilder()
		for (string in songContext) {
			stringBuilder.append("$string ")
		}

		val searchListByKeywordRequest: YouTube.Search.List = youTube.search().list("id")
		searchListByKeywordRequest.maxResults = 1
		searchListByKeywordRequest.type = "video"
		searchListByKeywordRequest.q = stringBuilder.toString()

		val response: SearchListResponse = searchListByKeywordRequest.execute()
		return response.items[0].id.videoId
	}

	private fun getYouTubeService(): YouTube {
		val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
		val jsonFactory = JacksonFactory.getDefaultInstance()
		return YouTube.Builder(httpTransport, jsonFactory, AuthorizationCodeInstalledApp(GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, GoogleClientSecrets.load(jsonFactory, InputStreamReader(FileInputStream("client_secret.json"))), Arrays.asList(YouTubeScopes.YOUTUBE_READONLY)).setDataStoreFactory(FileDataStoreFactory(java.io.File(System.getProperty("user.home"), ".credentials/bugisoft"))).setAccessType("offline").build(), LocalServerReceiver()).authorize("user")).setApplicationName("Bugisoft Track Finder").build()
	}
}