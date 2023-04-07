package com.pns.musicwiki.ui.genredetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pns.musicwiki.data.genredetails.tracks.Track
import com.pns.musicwiki.databinding.GenreDetailItemLayoutBinding

class TracksAdapter(private val trackList: MutableList<Track>) :
    RecyclerView.Adapter<TracksAdapter.GenreTracksViewHolder>() {

    inner class GenreTracksViewHolder(val binding: GenreDetailItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreTracksViewHolder {
        return GenreTracksViewHolder(
            GenreDetailItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreTracksViewHolder, position: Int) {

        val track = trackList[position]

        holder.binding.apply {
            title.text = track.name
            artist.text = track.name
            Glide.with(image).load(track.image.size - 1).into(image)
        }
    }

    override fun getItemCount(): Int {
        return trackList.size
    }
}