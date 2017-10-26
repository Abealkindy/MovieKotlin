package com.rosinante24.firstinthisversion.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rosinante24.firstinthisversion.Adapters.NowPlayingFragmentAdapter
import com.rosinante24.firstinthisversion.POKO.NowPlayingPOKO

import com.rosinante24.firstinthisversion.R
import com.rosinante24.firstinthisversion.Utils.EndPoints
import com.rosinante24.firstinthisversion.Utils.InitRetrofit
import kotlinx.android.synthetic.main.fragment_now_playing.*
import retrofit2.Call
import retrofit2.Callback


/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_now_playing, container, false)

        var swipe = view.findViewById<View>(R.id.refresh_now_playing) as SwipeRefreshLayout
        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            getNowPlayingData()
        }

        getNowPlayingData()
        return view
    }

    private fun getNowPlayingData() {
        var api = InitRetrofit().getInitInstance()
        var call = api.request_nowPlaying(EndPoints.API_KEY)
        call.enqueue(object : Callback<NowPlayingPOKO> {

            override fun onFailure(call: Call<NowPlayingPOKO>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<NowPlayingPOKO>?, response: retrofit2.Response<NowPlayingPOKO>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        var data = response.body()?.data
                        val adapter = NowPlayingFragmentAdapter(activity, data)
                        recycler_now_playing.adapter = adapter
                        recycler_now_playing.layoutManager = GridLayoutManager(activity, 2)
                    }
                }
            }

        })
    }

}
