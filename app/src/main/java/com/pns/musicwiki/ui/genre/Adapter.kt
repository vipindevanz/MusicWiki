package com.pns.musicwiki.ui.genre

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pns.musicwiki.data.Tag
import com.pns.musicwiki.databinding.GenreItemLayoutBinding
import com.pns.musicwiki.ui.genredetails.GenreDetailActivity

class Adapter(private var genreList: MutableList<Tag>, private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GenreItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val genre = genreList[position]

        holder.binding.apply {

            genreName.text = genre.name

            root.setOnClickListener {
                val intent = Intent(context, GenreDetailActivity::class.java)
                intent.putExtra("genre", genre.name)
                context.startActivity(intent)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataset(genreList: MutableList<Tag>) {
        this.genreList = genreList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: GenreItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}


