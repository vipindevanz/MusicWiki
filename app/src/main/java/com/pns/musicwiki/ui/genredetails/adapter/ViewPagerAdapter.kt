package com.pns.musicwiki.ui.genredetails.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pns.musicwiki.ui.genredetails.fragment.AlbumsFragment
import com.pns.musicwiki.ui.genredetails.fragment.ArtistsFragment
import com.pns.musicwiki.ui.genredetails.fragment.TracksFragment

class ViewPagerAdapter(activity: FragmentActivity, private var tittle: String) :
    FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                AlbumsFragment.genreName = tittle
                AlbumsFragment()
            }
            1 -> {
                ArtistsFragment.genreName = tittle
                ArtistsFragment()
            }
            2 -> {
                TracksFragment.genreName = tittle
                TracksFragment()
            }
            else -> {
                AlbumsFragment.genreName = tittle
                AlbumsFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}