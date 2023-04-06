package com.pns.musicwiki.data.network

import com.pns.musicwiki.data.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApi {

    @GET("/2.0")
    suspend fun getTopTags(
        @Query("method") method: String = "tag.getTopTags",
        @Query("api_key") apiKey: String = "c305ae29944dbfea31889c668799325d",
        @Query("format") format: String = "json"
    ): Response<GenreResponse>
}