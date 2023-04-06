package com.pns.musicwiki.ui.genredetails.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pns.musicwiki.data.genredetails.tracks.Track
import com.pns.musicwiki.databinding.FragmentTracksBinding
import com.pns.musicwiki.repository.Repository
import com.pns.musicwiki.ui.genredetails.adapter.TracksAdapter
import com.pns.musicwiki.viewmodel.MainViewModel
import com.pns.musicwiki.viewmodel.MainViewModelFactory

class TracksFragment(private var genreName: String) : Fragment() {

    private lateinit var binding: FragmentTracksBinding
    private lateinit var adapter: TracksAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var trackList: ArrayList<Track>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTracksBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getGenreTracks(genreName)
        viewModel.genreTracksResponse.observe(viewLifecycleOwner) { tracks ->

            trackList.clear()
            trackList.addAll(tracks)
            trackList.shuffle()
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupRecyclerView() {
        trackList = ArrayList()
        this.adapter = TracksAdapter(trackList)
        binding.recyclerview.adapter = adapter
    }
}