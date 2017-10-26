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
import com.rosinante24.firstinthisversion.POKO.UpcomingPOKO
import com.rosinante24.firstinthisversion.R
import com.rosinante24.firstinthisversion.Utils.EndPoints
import com.rosinante24.firstinthisversion.Utils.SquareLayout
import com.squareup.picasso.Picasso

/**
 * Created by Rosinante24 on 26/10/17.
 */
class UpcomingFragmentAdapter : RecyclerView.Adapter<UpcomingFragmentAdapter.ViewHolder> {
    var mMovieData: List<UpcomingPOKO.UpcomingData>? = null
    var mContext: Context? = null

    constructor(c: FragmentActivity?, data: List<UpcomingPOKO.UpcomingData>?) {
        this.mContext = c
        this.mMovieData = data
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        val upcomingData: UpcomingPOKO.UpcomingData = mMovieData!!.get(position)

        Picasso.with(mContext)
                .load(EndPoints.IMAGE_URL_POSTER + upcomingData.poster_path)
                .placeholder(R.drawable.placeholder)
                .into(holder!!.movieThumb)

        holder.squareLayout.setOnClickListener({ v ->
            val intent = Intent(mContext?.applicationContext, DetailActivity::class.java)
            intent.putExtra("id_movie", upcomingData.idMovie)
            intent.putExtra("title_movie", upcomingData.movieTitle)
            intent.putExtra("backdrop_movie", upcomingData.backdrop_path)
            intent.putExtra("overview_movie", upcomingData.overview)
            intent.putExtra("releasedate_movie", upcomingData.release_date)
            intent.putExtra("votesaverage_movie", upcomingData.vote_average)
            intent.putExtra("votecount_movie", upcomingData.vote_count)
            mContext!!.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return mMovieData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.upcoming_thumbnail, parent, false)

        return ViewHolder(inflater)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var movieThumb = itemView?.findViewById<View>(R.id.upcomingThumbnail) as ImageView
        var squareLayout = itemView?.findViewById<View>(R.id.sq_Upcoming) as SquareLayout
    }

}