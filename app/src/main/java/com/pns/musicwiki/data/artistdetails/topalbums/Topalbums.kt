package com.pns.musicwiki.data.artistdetails.topalbums

import com.google.gson.annotations.SerializedName
import com.pns.musicwiki.data.albumdetails.Attr

data class Topalbums(
    @SerializedName("@attr")
    val attr: Attr,
    val album: List<AlbumT>
)