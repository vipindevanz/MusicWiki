package com.pns.musicwiki.repository

import com.pns.musicwiki.data.GenreResponse
import com.pns.musicwiki.data.network.ApiClient
import retrofit2.Response

class Repository {
    suspend fun getTopTags(): Response<GenreResponse> {
        return ApiClient.getWikiApi().getTopTags()
    }
}