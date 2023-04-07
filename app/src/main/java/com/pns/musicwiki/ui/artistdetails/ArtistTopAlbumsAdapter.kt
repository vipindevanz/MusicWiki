package com.pns.musicwiki.ui.artistdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pns.musicwiki.data.artistdetails.topalbums.AlbumT
import com.pns.musicwiki.databinding.GenreDetailItemLayoutBinding

class ArtistTopAlbumsAdapter(
    private var context: Context,
    private var topTrackAlbumList: MutableList<AlbumT>
) : RecyclerView.Adapter<ArtistTopAlbumsAdapter.ArtistTopAlbumsViewHolder>() {
    inner class ArtistTopAlbumsViewHolder(val binding: GenreDetailItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistTopAlbumsViewHolder {
        return ArtistTopAlbumsViewHolder(
            GenreDetailItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistTopAlbumsViewHolder, position: Int) {

        holder.binding.apply {
            title.text = topTrackAlbumList[position].name
            artist.text = topTrackAlbumList[position].artist.name
            Glide.with(image).load(topTrackAlbumList[position].image[topTrackAlbumList[position].image.size-1].text).into(image)
        }
    }

    override fun getItemCount(): Int {
        return topTrackAlbumList.size
    }
}

