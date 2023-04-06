package com.pns.musicwiki.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pns.musicwiki.data.Tag
import com.pns.musicwiki.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _topTagResponse: MutableLiveData<List<Tag>> = MutableLiveData()
    val topTagResponse: LiveData<List<Tag>> = _topTagResponse

    init {
        viewModelScope.launch {
            val response = repository.getTopTags()
            if (response.isSuccessful) {
                _topTagResponse.value = response.body()!!.toptags.tag
            }
        }
    }
}