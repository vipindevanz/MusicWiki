package com.pns.musicwiki.data.genredetails.artist

import com.google.gson.annotations.SerializedName
import com.pns.musicwiki.data.genredetails.album.AttrX

data class Topartists(
    @SerializedName("@attr")
    val attr: AttrX,
    val artist: List<Artist>
)