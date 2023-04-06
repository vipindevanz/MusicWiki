package com.pns.musicwiki.data.genredetails.tracks

import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<Track>
)