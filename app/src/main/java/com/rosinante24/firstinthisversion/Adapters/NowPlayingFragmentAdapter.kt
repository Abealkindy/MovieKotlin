package com.rosinante24.firstinthisversion.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.rosinante24.firstinthisversion.Activities.DetailActivity
import com.rosinante24.firstinthisversion.POKO.NowPlayingPOKO
import com.rosinante24.firstinthisversion.R
import com.rosinante24.firstinthisversion.Utils.EndPoints
import com.rosinante24.firstinthisversion.Utils.SquareLayout
import com.squareup.picasso.Picasso

/**
 * Created by Rosinante24 on 26/10/17.
 */
class NowPlayingFragmentAdapter : RecyclerView.Adapter<NowPlayingFragmentAdapter.ViewHolder> {

    var mMovieData: List<NowPlayingPOKO.NowPlayingData>? = null
    var mContext: Context? = null

    constructor(c: FragmentActivity?, data: List<NowPlayingPOKO.NowPlayingData>?) {
        this.mContext = c
        this.mMovieData = data
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val nowPlayingData: NowPlayingPOKO.NowPlayingData = mMovieData!!.get(position)

        Picasso.with(mContext)
                .load(EndPoints.IMAGE_URL_POSTER + nowPlayingData.poster_path)
                .placeholder(R.drawable.placeholder)
                .into(holder!!.movieThumb)

        holder.squareLayout.setOnClickListener({ v ->
            val intent = Intent(mContext?.applicationContext, DetailActivity::class.java)
            intent.putExtra("id_movie", nowPlayingData.idMovie)
            intent.putExtra("title_movie", nowPlayingData.movieTitle)
            intent.putExtra("backdrop_movie", nowPlayingData.backdrop_path)
            intent.putExtra("overview_movie", nowPlayingData.overview)
            intent.putExtra("releasedate_movie", nowPlayingData.release_date)
            intent.putExtra("votesaverage_movie", nowPlayingData.vote_average)
            intent.putExtra("votecount_movie", nowPlayingData.vote_count)
            mContext!!.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return mMovieData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.nowplaying_thumbnail, parent, false)

        return ViewHolder(inflater)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var movieThumb = itemView?.findViewById<View>(R.id.nowPlayingThumbnail) as ImageView
        var squareLayout = itemView?.findViewById<View>(R.id.sq_NowPlaying) as SquareLayout
    }


}