package com.pns.musicwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pns.musicwiki.data.albumdetails.AlbumD
import com.pns.musicwiki.data.artistdetails.topalbums.AlbumT
import com.pns.musicwiki.data.artistdetails.toptracks.TrackT
import com.pns.musicwiki.data.genre.Tag
import com.pns.musicwiki.data.genredetails.TagAlbum
import com.pns.musicwiki.data.genredetails.album.Album
import com.pns.musicwiki.data.genredetails.artist.Artist
import com.pns.musicwiki.data.genredetails.tracks.Track
import com.pns.musicwiki.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _genreResponse: MutableLiveData<List<Tag>> = MutableLiveData()
    val genreResponse: LiveData<List<Tag>> = _genreResponse

    private val _genreDetailsResponse: MutableLiveData<TagAlbum> = MutableLiveData()
    val genreDetailsResponse: LiveData<TagAlbum> = _genreDetailsResponse

    private val _genreAlbumsResponse: MutableLiveData<List<Album>> = MutableLiveData()
    val genreAlbumsResponse: LiveData<List<Album>> = _genreAlbumsResponse

    private val _genreArtistsResponse: MutableLiveData<List<Artist>> = MutableLiveData()
    val genreArtistsResponse: LiveData<List<Artist>> = _genreArtistsResponse

    private val _genreTracksResponse: MutableLiveData<List<Track>> = MutableLiveData()
    val genreTracksResponse: LiveData<List<Track>> = _genreTracksResponse

    private val _albumDetailsResponse: MutableLiveData<AlbumD> = MutableLiveData()
    val albumDetailsResponse: LiveData<AlbumD> = _albumDetailsResponse

    private val _artistInfoResponse: MutableLiveData<com.pns.musicwiki.data.artistdetails.Artist> =
        MutableLiveData()
    val artistInfoResponse: LiveData<com.pns.musicwiki.data.artistdetails.Artist> =
        _artistInfoResponse

    private val _artistTopAlbumsResponse: MutableLiveData<List<AlbumT>> = MutableLiveData()
    val artistTopAlbumsResponse: LiveData<List<AlbumT>> = _artistTopAlbumsResponse

    private val _artistTopTracksResponse: MutableLiveData<List<TrackT>> = MutableLiveData()
    val artistTopTracksResponse: LiveData<List<TrackT>> = _artistTopTracksResponse

    init {
        viewModelScope.launch {
            val response = repository.getGenres()
            if (response.isSuccessful) {
                _genreResponse.value = response.body()!!.toptags.tag
            }
        }
    }

    fun getGenreDetails(genre: String) {
        viewModelScope.launch {
            val response = repository.getGenresDetails(genre)
            if (response.isSuccessful) {
                _genreDetailsResponse.value = response.body()!!.tag
            }
        }
    }

    fun getGenreAlbums(genre: String) {
        viewModelScope.launch {
            val response = repository.getGenreAlbum(genre)
            if (response.isSuccessful) {
                _genreAlbumsResponse.value = response.body()!!.albums.album
            }
        }
    }

    fun getGenreArtists(genre: String) {
        viewModelScope.launch {
            val response = repository.getGenreArtist(genre)
            if (response.isSuccessful) {
                _genreArtistsResponse.value = response.body()!!.topartists.artist
            }
        }
    }

    fun getGenreTracks(genre: String) {
        viewModelScope.launch {
            val response = repository.getGenreATracks(genre)
            if (response.isSuccessful) {
                _genreTracksResponse.value = response.body()!!.tracks.track
            }
        }
    }

    fun getAlbumDetails(artist: String, album: String) {
        viewModelScope.launch {
            val response = repository.getAlbumDetails(artist, album)
            if (response.isSuccessful) {
                _albumDetailsResponse.value = response.body()!!.album
            }
        }
    }

    fun getArtistInfo(artist: String) {
        viewModelScope.launch {
            val response = repository.getArtistInfo(artist)
            if (response.isSuccessful) {
                _artistInfoResponse.value = response.body()!!.artist
            }

        }
    }

    fun getArtistTopAlbums(artist: String) {
        viewModelScope.launch {
            val response = repository.getArtistTopAlbums(artist)
            if (response.isSuccessful) {
                _artistTopAlbumsResponse.value = response.body()!!.topalbums.album
            }

        }
    }

    fun getArtistTopTracks(artist: String) {
        viewModelScope.launch {
            val response = repository.getArtistTopTracks(artist)
            if (response.isSuccessful) {
                _artistTopTracksResponse.value = response.body()!!.toptracks.track
            }

        }
    }
}