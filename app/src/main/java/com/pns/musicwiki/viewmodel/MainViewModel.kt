package com.pns.musicwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}