package com.pns.musicwiki.data.artistdetails.toptracks

import com.google.gson.annotations.SerializedName
import com.pns.musicwiki.data.albumdetails.Attr

data class TrackT(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: Artist,
    val image: List<Image>,
    val listeners: String,
    val name: String,
    val playcount: String,
    val streamable: String,
    val url: String
)