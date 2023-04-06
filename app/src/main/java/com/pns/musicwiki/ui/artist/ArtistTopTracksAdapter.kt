package com.pns.musicwiki.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pns.musicwiki.data.artistdetails.toptracks.TrackT
import com.pns.musicwiki.databinding.GenreDetailItemLayoutBinding

class ArtistTopTracksAdapter(private var topTrackList: MutableList<TrackT>) :
    RecyclerView.Adapter<ArtistTopTracksAdapter.AristTopTracksViewHolder>() {

    inner class AristTopTracksViewHolder(val binding: GenreDetailItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AristTopTracksViewHolder {
        return AristTopTracksViewHolder(
            GenreDetailItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AristTopTracksViewHolder, position: Int) {

        holder.binding.apply {
            title.text = topTrackList[position].name
            artist.text = topTrackList[position].artist.name
            Glide.with(image).load(topTrackList[position].image[0].text).into(image)
        }
    }

    override fun getItemCount(): Int {
        return topTrackList.size
    }
}