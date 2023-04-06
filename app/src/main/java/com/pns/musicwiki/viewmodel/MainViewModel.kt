package com.pns.musicwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pns.musicwiki.data.genre.Tag
import com.pns.musicwiki.data.genredetails.TagAlbum
import com.pns.musicwiki.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _genreResponse: MutableLiveData<List<Tag>> = MutableLiveData()
    val genreResponse: LiveData<List<Tag>> = _genreResponse

    private val _genreDetailsResponse : MutableLiveData<TagAlbum> = MutableLiveData()
    val genreDetailsResponse: LiveData<TagAlbum> = _genreDetailsResponse

    init {
        viewModelScope.launch {
            val response = repository.getGenres()
            if (response.isSuccessful) {
                _genreResponse.value = response.body()!!.toptags.tag
            }
        }
    }

    fun getGenreDetails(genre : String){
        viewModelScope.launch {
            val response = repository.getGenresDetails(genre)
            if(response.isSuccessful){
                _genreDetailsResponse.value = response.body()!!.tag
            }

        }
    }
}