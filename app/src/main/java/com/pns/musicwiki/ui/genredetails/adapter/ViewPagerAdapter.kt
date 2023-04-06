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
            0 -> AlbumsFragment(tittle)
            1 -> ArtistsFragment(tittle)
            2 -> TracksFragment(tittle)
            else -> {
                AlbumsFragment(tittle)
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}