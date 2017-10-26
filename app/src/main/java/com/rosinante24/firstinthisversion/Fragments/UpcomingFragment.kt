package com.rosinante24.firstinthisversion.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rosinante24.firstinthisversion.Adapters.UpcomingFragmentAdapter
import com.rosinante24.firstinthisversion.POKO.UpcomingPOKO
import com.rosinante24.firstinthisversion.R
import com.rosinante24.firstinthisversion.Utils.EndPoints
import com.rosinante24.firstinthisversion.Utils.InitRetrofit
import kotlinx.android.synthetic.main.fragment_upcoming.*
import retrofit2.Call
import retrofit2.Callback


/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_upcoming, container, false)
        getUpcomingData()
        return view
    }

    private fun getUpcomingData() {

        var api = InitRetrofit().getInitInstance()
        var call = api.request_upcoming(EndPoints.API_KEY)
        call.enqueue(object : Callback<UpcomingPOKO> {

            override fun onFailure(call: Call<UpcomingPOKO>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<UpcomingPOKO>?, response: retrofit2.Response<UpcomingPOKO>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        var data = response.body()?.data
                        val adapter = UpcomingFragmentAdapter(activity, data)
                        recyler_upcoming.adapter = adapter
                        recyler_upcoming.layoutManager = GridLayoutManager(activity, 2)
                    }
                }
            }

        })

    }

}
