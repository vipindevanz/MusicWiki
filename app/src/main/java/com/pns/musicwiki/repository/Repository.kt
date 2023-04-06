package com.pns.musicwiki.repository

import com.pns.musicwiki.data.albumdetails.AlbumDetailsResponse
import com.pns.musicwiki.data.genre.GenreResponse
import com.pns.musicwiki.data.genredetails.GenreDetailsResponse
import com.pns.musicwiki.data.genredetails.album.AlbumResponse
import com.pns.musicwiki.data.genredetails.artist.ArtistResponse
import com.pns.musicwiki.data.genredetails.tracks.TrackResponse
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

    suspend fun getGenreArtist(genre: String): Response<ArtistResponse> {
        return ApiClient.getWikiApi().getArtists(genre)
    }

    suspend fun getGenreATracks(genre: String): Response<TrackResponse> {
        return ApiClient.getWikiApi().getTracks(genre)
    }

    suspend fun getAlbumDetails(artist: String, album: String): Response<AlbumDetailsResponse> {
        return ApiClient.getWikiApi().getAlbumDetails(artist, album)
    }
}