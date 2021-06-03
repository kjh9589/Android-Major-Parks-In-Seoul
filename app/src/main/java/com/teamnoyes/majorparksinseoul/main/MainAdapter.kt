package com.teamnoyes.majorparksinseoul.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.teamnoyes.majorparksinseoul.main.bookmark.BookmarkFragment
import com.teamnoyes.majorparksinseoul.main.parks.ParksFragment

class MainAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val COUNT = 2
    override fun getItemCount(): Int = COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ParksFragment()
            1 -> BookmarkFragment()
            else -> ParksFragment()
        }
    }
}