package com.pns.musicwiki.repository

import com.pns.musicwiki.data.genre.GenreResponse
import com.pns.musicwiki.data.genredetails.GenreDetailsResponse
import com.pns.musicwiki.data.genredetails.album.AlbumResponse
import com.pns.musicwiki.data.network.ApiClient
import retrofit2.Response

class Repository {

    suspend fun getGenres(): Response<GenreResponse> {
        return ApiClient.getWikiApi().getGenres()
    }

    suspend fun getGenresDetails(genre: String): Response<GenreDetailsResponse> {
        return ApiClient.getWikiApi().getGenresDetails(genre)
    }

    suspend fun getGenreAlbum(genre: String): Response<AlbumResponse> {
        return ApiClient.getWikiApi().getAlbums(genre)
    }
}