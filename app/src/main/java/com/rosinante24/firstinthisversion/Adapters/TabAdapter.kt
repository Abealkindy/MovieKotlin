package com.rosinante24.firstinthisversion.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rosinante24.firstinthisversion.Fragments.NowPlayingFragment
import com.rosinante24.firstinthisversion.Fragments.UpcomingFragment

/**
 * Created by Rosinante24 on 26/10/17.
 */
class TabAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    // mengatur letak tabLayout sesuai posisi dan fragment yang akan ditampilkan
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return NowPlayingFragment()
        } else {
            return UpcomingFragment()
        }
    }

    // mengambil total dari fragment atau tampilan
    override fun getCount(): Int {
        return 2
    }

}