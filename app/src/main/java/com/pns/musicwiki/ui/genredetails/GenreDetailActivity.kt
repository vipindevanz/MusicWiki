package com.pns.musicwiki.ui.genredetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.pns.musicwiki.data.genredetails.TagAlbum
import com.pns.musicwiki.databinding.ActivityGenreDetailBinding
import com.pns.musicwiki.repository.Repository
import com.pns.musicwiki.ui.genredetails.adapter.ViewPagerAdapter
import com.pns.musicwiki.viewmodel.MainViewModel
import com.pns.musicwiki.viewmodel.MainViewModelFactory

class GenreDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGenreDetailBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGenreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val genre = intent?.getStringExtra("genre")

        viewPagerAdapter = ViewPagerAdapter(this, genre.toString())
        binding.viewpager.adapter = viewPagerAdapter

        setupTabLayout()
        registerViewPager()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getGenreDetails(genre.toString())

        viewModel.genreDetailsResponse.observe(this) { info: TagAlbum ->
            binding.genre.text = info.name
            binding.genreSummary.text = info.wiki.summary
        }
    }

    private fun setupTabLayout() {

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewpager.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun registerViewPager() {
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.getTabAt(position)!!.select()
            }
        })
    }
}