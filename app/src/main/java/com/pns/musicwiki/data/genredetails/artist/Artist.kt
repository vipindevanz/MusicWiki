package com.pns.musicwiki.data.genredetails.artist

import com.google.gson.annotations.SerializedName
import com.pns.musicwiki.data.genredetails.album.AttrX

data class Artist(
    @SerializedName("@attr")
    val attr: AttrX,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)