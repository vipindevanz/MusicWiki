package com.pns.musicwiki.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ws.audioscrobbler.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWikiApi(): WikiApi {
        return retrofit.create(WikiApi::class.java)
    }
}