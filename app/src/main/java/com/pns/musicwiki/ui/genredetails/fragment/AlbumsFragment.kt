package com.pns.musicwiki.ui.genredetails.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pns.musicwiki.data.genredetails.album.Album
import com.pns.musicwiki.databinding.FragmentAlbumsBinding
import com.pns.musicwiki.repository.Repository
import com.pns.musicwiki.ui.genredetails.adapter.AlbumsAdapter
import com.pns.musicwiki.viewmodel.MainViewModel
import com.pns.musicwiki.viewmodel.MainViewModelFactory

class AlbumsFragment(private var genreName: String) : Fragment() {

    private lateinit var binding: FragmentAlbumsBinding
    private lateinit var adapter: AlbumsAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var albumList: ArrayList<Album>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getGenreAlbums(genreName)
        viewModel.genreAlbumsResponse.observe(viewLifecycleOwner) { albums ->

            albumList.clear()
            albumList.addAll(albums)
            albumList.shuffle()
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {

        albumList = ArrayList()
        this.adapter = context?.let { AlbumsAdapter(it, albumList) }!!
        binding.recyclerview.adapter = adapter
    }
}