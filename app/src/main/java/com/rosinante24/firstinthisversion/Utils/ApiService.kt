package com.rosinante24.firstinthisversion.Utils

import com.rosinante24.firstinthisversion.POKO.DetaiPOKO
import com.rosinante24.firstinthisversion.POKO.NowPlayingPOKO
import com.rosinante24.firstinthisversion.POKO.TrailersPOKO
import com.rosinante24.firstinthisversion.POKO.UpcomingPOKO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Rosinante24 on 26/10/17.
 */
interface ApiService {

    @GET("now_playing")
    fun request_nowPlaying(
            @Query("api_key") apiKey: String
    ): Call<NowPlayingPOKO>

    @GET("upcoming")
    fun request_upcoming(
            @Query("api_key") apiKey: String
    ): Call<UpcomingPOKO>

    @GET("{detail_id}")
    fun request_detail(
            @Path("detail_id") movieId: Int,
            @Query("api_key") apiKey: String
    ): Call<DetaiPOKO>


    @GET("{trailer_id}/videos")
    fun request_trailer(
            @Path("trailer_id") trailerId: Int,
            @Query("api_key") apiKey: String
    ): Call<TrailersPOKO>


}