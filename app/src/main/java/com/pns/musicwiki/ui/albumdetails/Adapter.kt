package com.pns.musicwiki.ui.albumdetails

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pns.musicwiki.data.albumdetails.Tag
import com.pns.musicwiki.databinding.GenreItemBinding
import com.pns.musicwiki.ui.genredetails.GenreDetailActivity

class Adapter(private var context: Context, private var albumDetailsList: MutableList<Tag>) :
    RecyclerView.Adapter<Adapter.AlbumInfoGenreViewHolder>() {
    inner class AlbumInfoGenreViewHolder(val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumInfoGenreViewHolder {
        return AlbumInfoGenreViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false // fkedlfkeeeee
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumInfoGenreViewHolder, position: Int) {

        val albumDetail = albumDetailsList[position]

        holder.binding.apply {
            genreName.text = albumDetail.name
            root.setOnClickListener {
                val mIntent = Intent(context, GenreDetailActivity::class.java)
                mIntent.putExtra("genre", albumDetail.name)
                context.startActivity(mIntent)
            }
        }
    }

    override fun getItemCount(): Int {
        return albumDetailsList.size
    }
}