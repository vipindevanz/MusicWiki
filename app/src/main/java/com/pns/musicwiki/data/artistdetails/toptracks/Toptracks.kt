package com.pns.musicwiki.data.artistdetails.toptracks

import com.google.gson.annotations.SerializedName
import com.pns.musicwiki.data.albumdetails.Attr

data class Toptracks(
    @SerializedName("@attr")
    val attr: Attr,
    val track: List<TrackT>
)