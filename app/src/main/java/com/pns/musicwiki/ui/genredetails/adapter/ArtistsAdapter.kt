package com.pns.musicwiki.ui.genredetails.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pns.musicwiki.data.genredetails.artist.Artist
import com.pns.musicwiki.databinding.GenreDetailItemLayoutBinding
import com.pns.musicwiki.ui.artist.ArtistActivity

class ArtistsAdapter(var context: Context, private var artistList: MutableList<Artist>) :
    RecyclerView.Adapter<ArtistsAdapter.GenreArtistsViewHolder>() {

    inner class GenreArtistsViewHolder(val binding: GenreDetailItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreArtistsViewHolder {
        return GenreArtistsViewHolder(
            GenreDetailItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreArtistsViewHolder, position: Int) {

        val artist = artistList[position]

        holder.binding.apply {
            this.artist.text = artist.name
            title.visibility = View.GONE
            Glide.with(context).load(artist.image[0].text).into(image)
        }

        holder.binding.root.setOnClickListener {
            val mIntent = Intent(context, ArtistActivity::class.java)
            mIntent.putExtra("artist", artist.name)
            context.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return artistList.size
    }
}