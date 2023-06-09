package com.pns.musicwiki.ui.genredetails.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pns.musicwiki.data.genredetails.artist.Artist
import com.pns.musicwiki.databinding.FragmentArtistsBinding
import com.pns.musicwiki.repository.Repository
import com.pns.musicwiki.ui.genredetails.adapter.ArtistsAdapter
import com.pns.musicwiki.viewmodel.MainViewModel
import com.pns.musicwiki.viewmodel.MainViewModelFactory

class ArtistsFragment : Fragment() {

    private lateinit var binding: FragmentArtistsBinding
    private lateinit var adapter: ArtistsAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var artistList: ArrayList<Artist>

    companion object {
        var genreName : String ?= null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArtistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        genreName?.let { viewModel.getGenreArtists(it) }
        viewModel.genreArtistsResponse.observe(viewLifecycleOwner) { artists ->

            artistList.clear()
            artistList.addAll(artists)
            artistList.shuffle()
            adapter.notifyDataSetChanged()

            if (artistList.isEmpty()){
                Toast.makeText(context, "No response", Toast.LENGTH_LONG).show()
            } else{
                binding.progressBar.visibility = View.GONE
                binding.recyclerview.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        artistList = ArrayList()
        this.adapter = context?.let { ArtistsAdapter(it, artistList) }!!
        binding.recyclerview.adapter = adapter
    }
}