package com.rosinante24.firstinthisversion.Adapters

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.rosinante24.firstinthisversion.POKO.TrailersPOKO
import com.rosinante24.firstinthisversion.R
import com.squareup.picasso.Picasso

/**
 * Created by Rosinante24 on 26/10/17.
 */
class TrailersAdapter : RecyclerView.Adapter<TrailersAdapter.ViewHolder> {
    var mTrailerData: List<TrailersPOKO.Data>? = null
    var mContext: Context? = null

    constructor(c: FragmentActivity?, data: List<TrailersPOKO.Data>?) {
        this.mContext = c
        this.mTrailerData = data
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val trailerData: TrailersPOKO.Data = mTrailerData!!.get(position)

        Picasso.with(mContext)
                .load("http://img.youtube.com/vi/${trailerData.key}/hqdefault.jpg")
                .into(holder!!.imgTrailer)

        holder.imgTrailer.setOnClickListener({ v ->
            watchYoutube(trailerData.key)
        })

    }

    override fun getItemCount(): Int {
        return mTrailerData!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(mContext).inflate(R.layout.trailer_item, parent, false)

        return ViewHolder(inflater)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var imgTrailer = itemView?.findViewById<View>(R.id.img_trailer) as ImageView
    }

    private fun watchYoutube(id: String?) {
        val appIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id))
        val webIntent: Intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id))

        try {
            mContext!!.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            mContext!!.startActivity(webIntent)
        }
    }

}