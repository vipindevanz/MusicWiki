package com.pns.musicwiki.ui.genredetails.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pns.musicwiki.data.genredetails.tracks.Track
import com.pns.musicwiki.databinding.FragmentTracksBinding
import com.pns.musicwiki.repository.Repository
import com.pns.musicwiki.ui.genredetails.adapter.TracksAdapter
import com.pns.musicwiki.viewmodel.MainViewModel
import com.pns.musicwiki.viewmodel.MainViewModelFactory

class TracksFragment : Fragment() {

    private lateinit var binding: FragmentTracksBinding
    private lateinit var adapter: TracksAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var trackList: ArrayList<Track>

    companion object {
        var genreName : String ?= null
    }

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

        genreName?.let { viewModel.getGenreTracks(it) }
        viewModel.genreTracksResponse.observe(viewLifecycleOwner) { tracks ->

            trackList.clear()
            trackList.addAll(tracks)
            trackList.shuffle()
            adapter.notifyDataSetChanged()

            if (trackList.isEmpty()){
                Toast.makeText(context, "No response", Toast.LENGTH_LONG).show()
            } else{
                binding.progressBar.visibility = View.GONE
                binding.recyclerview.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        trackList = ArrayList()
        this.adapter = TracksAdapter(trackList)
        binding.recyclerview.adapter = adapter
    }
}