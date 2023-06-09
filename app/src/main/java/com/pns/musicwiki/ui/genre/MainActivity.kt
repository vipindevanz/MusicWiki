package com.pns.musicwiki.ui.genre

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.pns.musicwiki.R
import com.pns.musicwiki.data.genre.Tag
import com.pns.musicwiki.databinding.ActivityMainBinding
import com.pns.musicwiki.repository.Repository
import com.pns.musicwiki.viewmodel.MainViewModel
import com.pns.musicwiki.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var genreList: ArrayList<Tag>
    private var isGenreListExpanded = false

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.genreResponse.observe(this) { genres ->

            genreList.clear()
            genreList.addAll(genres)
            genreList.shuffle()

            if (genreList.isEmpty()){
                Toast.makeText(this@MainActivity, "No response", Toast.LENGTH_LONG).show()
            } else{
                binding.progressBar.visibility = View.GONE
                binding.recyclerview.visibility = View.VISIBLE
            }

            if (genreList.size > 10) {
                adapter.updateDataset(genreList.subList(0, 10))
            } else {
                adapter.updateDataset(genreList)
            }
        }
    }

    private fun initViews() {

        genreList = ArrayList()
        adapter = Adapter(genreList, this)
        binding.recyclerview.adapter = adapter

        binding.dropDownBtn.apply {

            setOnClickListener {

                if (isGenreListExpanded) {
                    isGenreListExpanded = false
                    setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_keyboard_arrow_down,
                            null
                        )
                    )
                    adapter.updateDataset(genreList.subList(0, 10))
                } else {
                    isGenreListExpanded = true
                    setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_keyboard_arrow_up,
                            null
                        )
                    )
                    adapter.updateDataset(genreList)
                }
            }
        }
    }
}