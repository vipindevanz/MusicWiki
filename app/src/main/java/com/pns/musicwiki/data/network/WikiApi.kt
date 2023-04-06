package com.pns.musicwiki.data.network

import com.pns.musicwiki.data.genre.GenreResponse
import com.pns.musicwiki.data.genredetails.GenreDetailsResponse
import com.pns.musicwiki.data.genredetails.album.AlbumResponse
import com.pns.musicwiki.data.genredetails.artist.ArtistResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("/2.0")
    suspend fun getGenres(
        @Query("method") method: String = "tag.getTopTags",
        @Query("api_key") apiKey: String = "c305ae29944dbfea31889c668799325d",
        @Query("format") format: String = "json"
    ): Response<GenreResponse>

    @GET("/2.0")
    suspend fun getGenresDetails(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getInfo",
        @Query("api_key") apiKey:String="c305ae29944dbfea31889c668799325d",
        @Query("format") format:String="json"
    ): Response<GenreDetailsResponse>

    @GET("/2.0")
    suspend fun getAlbums(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopAlbums",
        @Query("api_key") apiKey:String="c305ae29944dbfea31889c668799325d",
        @Query("format") format:String="json"
    ): Response<AlbumResponse>

    @GET("/2.0")
    suspend fun getArtists(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopArtists",
        @Query("api_key") apiKey:String="c305ae29944dbfea31889c668799325d",
        @Query("format") format:String="json"
    ): Response<ArtistResponse>
}