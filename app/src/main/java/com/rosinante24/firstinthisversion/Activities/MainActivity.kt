package com.rosinante24.firstinthisversion.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.rosinante24.firstinthisversion.Adapters.TabAdapter
import com.rosinante24.firstinthisversion.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
//      untuk memberi title pada masing2 tabLayout
        tabHome.addTab(tabHome.newTab().setText("Now Playing"))
        tabHome.addTab(tabHome.newTab().setText("Upcoming"))
//      menginisialisasi adapter
        var adapter = TabAdapter(supportFragmentManager)
        viewPagerTabs.adapter = adapter
//      mengatur viePager ketika berpindah tempat
        viewPagerTabs.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabHome))

        tabHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPagerTabs.currentItem = tab?.position!!
            }

        })

    }
}
