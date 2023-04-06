package com.pns.musicwiki.data.genredetails.album

import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("@attr")
    val attr: Attr,
    val album: List<Album>
)