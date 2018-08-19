package com.beyondbell.bugisoft.music

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.YouTubeScopes
import com.google.api.services.youtube.model.SearchListResponse
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Arrays

object TrackFinder {
	private val DATA_STORE_DIR = java.io.File(System.getProperty("user.home"), ".credentials/bugisoft")
	private val DATA_STORE_FACTORY: FileDataStoreFactory = FileDataStoreFactory(DATA_STORE_DIR)
	private val JSON_FACTORY = JacksonFactory.getDefaultInstance()
	private val HTTP_TRANSPORT: HttpTransport = GoogleNetHttpTransport.newTrustedTransport()
	private val SCOPES = Arrays.asList(YouTubeScopes.YOUTUBE_READONLY)

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
		val credential = authorize()
		return YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName("Bugisoft Track Finder")
				.build()
	}

	private fun authorize(): Credential {
		val resource = FileInputStream("client_secret.json")
		val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(resource))

		val flow = GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline")
				.build()
		return AuthorizationCodeInstalledApp(
				flow, LocalServerReceiver()).authorize("user")
	}
}