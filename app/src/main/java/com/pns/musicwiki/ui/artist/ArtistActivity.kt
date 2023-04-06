package com.pns.musicwiki.ui.artist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.pns.musicwiki.R
import com.pns.musicwiki.data.artistdetails.Tag
import com.pns.musicwiki.data.artistdetails.topalbums.AlbumT
import com.pns.musicwiki.data.artistdetails.toptracks.TrackT
import com.pns.musicwiki.databinding.ActivityArtistBinding
import com.pns.musicwiki.repository.Repository
import com.pns.musicwiki.viewmodel.MainViewModel
import com.pns.musicwiki.viewmodel.MainViewModelFactory

class ArtistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistBinding
    lateinit var albumInfoGenreAdapter: AlbumGenreAdapter
    lateinit var artistTopTracksAdapter: ArtistTopTracksAdapter
    lateinit var artistTopAlbumsAdapter: ArtistTopAlbumsAdapter
    private lateinit var viewModel: MainViewModel

    private lateinit var albumDetailsList: MutableList<Tag>
    private lateinit var topTrackList: MutableList<TrackT>
    private lateinit var topAlbumList: MutableList<AlbumT>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        binding = ActivityArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGenreRecyclerView()
        setupTopTrackRecyclerView()
        setupTopAlbumRecyclerView()

        val aints = intent
        //  val aname = aints.getStringExtra("ANAME")
        val arname = aints.getStringExtra("artist")

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getArtistInfo(arname.toString())
        viewModel.artistInfoResponse.observe(this) { response ->

            albumDetailsList.clear()
            albumDetailsList.addAll(response.tags.tag)
            albumDetailsList.shuffle()
            albumInfoGenreAdapter.notifyDataSetChanged()

            binding.title.text = response.name
            binding.desc.text = response.bio.toString()
            binding.playCount.text = response.stats.playcount
            binding.followers.text = response.stats.listeners
            Glide.with(this@ArtistActivity).load(response.image[0].text).into(binding.image)
        }

        viewModel.getArtistTopTracks(arname.toString())

        viewModel.artistTopTracksResponse.observe(this) { topTracks ->

            topTrackList.clear()
            topTrackList.addAll(topTracks)
            topTrackList.shuffle()
            artistTopTracksAdapter.notifyDataSetChanged()
        }

        viewModel.getArtistTopAlbums(arname.toString())
        viewModel.artistTopAlbumsResponse.observe(this) { topAlbums ->

            topAlbumList.clear()
            topAlbumList.addAll(topAlbums)
            topAlbumList.shuffle()
            artistTopAlbumsAdapter.notifyDataSetChanged()
        }
    }

    private fun setupGenreRecyclerView() {
        albumDetailsList = ArrayList()
        albumInfoGenreAdapter = AlbumGenreAdapter(this@ArtistActivity, albumDetailsList)
        binding.genreRecyclerview.adapter = albumInfoGenreAdapter
    }

    private fun setupTopTrackRecyclerView() {
        topTrackList = ArrayList()
        artistTopTracksAdapter = ArtistTopTracksAdapter(topTrackList)
        binding.tracksRecyclerview.adapter = artistTopTracksAdapter
    }

    private fun setupTopAlbumRecyclerView() {
        topAlbumList = ArrayList()
        artistTopAlbumsAdapter = ArtistTopAlbumsAdapter(this@ArtistActivity, topAlbumList)
        binding.albumsRecyclerview.adapter = artistTopAlbumsAdapter
    }
}