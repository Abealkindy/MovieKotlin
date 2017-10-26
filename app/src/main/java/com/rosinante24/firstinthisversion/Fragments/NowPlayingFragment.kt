package com.rosinante24.firstinthisversion.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rosinante24.firstinthisversion.Adapters.NowPlayingFragmentAdapter
import com.rosinante24.firstinthisversion.Adapters.UpcomingFragmentAdapter
import com.rosinante24.firstinthisversion.POKO.NowPlayingPOKO
import com.rosinante24.firstinthisversion.POKO.UpcomingPOKO

import com.rosinante24.firstinthisversion.R
import com.rosinante24.firstinthisversion.Utils.EndPoints
import com.rosinante24.firstinthisversion.Utils.InitRetrofit
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_upcoming.*
import retrofit2.Call
import retrofit2.Callback


/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_now_playing, container, false)

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
