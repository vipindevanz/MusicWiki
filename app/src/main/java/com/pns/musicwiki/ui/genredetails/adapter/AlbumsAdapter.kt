package com.pns.musicwiki.ui.genredetails.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pns.musicwiki.data.genredetails.album.Album
import com.pns.musicwiki.databinding.GenreDetailItemLayoutBinding
import com.pns.musicwiki.ui.album.AlbumActivity

class AlbumsAdapter(private var mContext: Context, private var albumList: MutableList<Album>) :
    RecyclerView.Adapter<AlbumsAdapter.GenreAlbumsViewHolder>() {

    inner class GenreAlbumsViewHolder(val binding: GenreDetailItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAlbumsViewHolder {
        return GenreAlbumsViewHolder(
            GenreDetailItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreAlbumsViewHolder, position: Int) {

        holder.binding.apply {

            val album = albumList[position]
            title.text = album.name
            artist.text = album.artist.name
            Glide.with(mContext).load(album.image[0].text).into(image)
        }

        holder.binding.root.setOnClickListener {
            val mIntent = Intent(mContext, AlbumActivity::class.java)
            mIntent.putExtra("tittle", albumList[position].name)
            mIntent.putExtra("artist", albumList[position].artist.name)
            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }
}