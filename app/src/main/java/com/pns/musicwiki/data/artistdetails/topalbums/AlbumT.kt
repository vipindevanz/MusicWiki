package com.pns.musicwiki.data.artistdetails.topalbums

data class AlbumT(
    val artist: Artist,
    val image: List<Image>,
    val name: String,
    val playcount: Int,
    val url: String
)