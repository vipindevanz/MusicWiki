package com.pns.musicwiki.ui.albumdetails

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.pns.musicwiki.data.albumdetails.Tag
import com.pns.musicwiki.databinding.ActivityAlbumBinding
import com.pns.musicwiki.repository.Repository
import com.pns.musicwiki.viewmodel.MainViewModel
import com.pns.musicwiki.viewmodel.MainViewModelFactory

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding
    private lateinit var adapter: Adapter
    private lateinit var viewModel: MainViewModel
    private lateinit var albumDetailsList: MutableList<Tag>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener { finish() }

        setupRecyclerView()

        val info = intent
        val title = info.getStringExtra("title")
        val artist = info.getStringExtra("artist")

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getAlbumDetails(artist.toString(), title.toString())

        viewModel.albumDetailsResponse.observe(this) { albumDetails ->

            albumDetailsList.clear()
            albumDetailsList.addAll(albumDetails.tags.tag)
            albumDetailsList.shuffle()
            adapter.notifyDataSetChanged()

            binding.title.text = albumDetails.name
            binding.artist.text = albumDetails.artist
            binding.description.text = albumDetails.wiki.summary
            Glide.with(this@AlbumActivity).load(albumDetails.image[0].text).into(binding.image)
        }
    }

    private fun setupRecyclerView() {
        albumDetailsList = ArrayList()
        this.adapter = Adapter(this@AlbumActivity, albumDetailsList)
        binding.recyclerview.adapter = adapter
    }
}