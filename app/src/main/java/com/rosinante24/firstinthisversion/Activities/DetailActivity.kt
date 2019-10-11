package com.rosinante24.firstinthisversion.Activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.format.DateUtils
import android.text.format.Time
import android.view.View
import com.rosinante24.firstinthisversion.Adapters.TrailersAdapter
import com.rosinante24.firstinthisversion.POKO.DetaiPOKO
import com.rosinante24.firstinthisversion.POKO.TrailersPOKO
import com.rosinante24.firstinthisversion.R
import com.rosinante24.firstinthisversion.Utils.EndPoints
import com.rosinante24.firstinthisversion.Utils.InitRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.movie_about_description.*
import retrofit2.Call
import retrofit2.Callback

class DetailActivity : AppCompatActivity() {

    var releaseDateMovie: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
//      mengatur tampilan untuk fullscreen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
//      Menonaktifkan title pada ActionBar
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//      Menangkap semua data yang diambil dari Intent
        val idMovie = intent.getIntExtra("id_movie", 0)
        val titleMovie = intent.getStringExtra("title_movie")
        val backdropMovie = intent.getStringExtra("backdrop_movie")
        val overviewMovie = intent.getStringExtra("overview_movie")
        releaseDateMovie = intent.getStringExtra("releasedate_movie")
        val votesaverageMovie = intent.getDoubleExtra("votesaverage_movie", 0.00)
        val votecountMovie = intent.getIntExtra("votecount_movie", 0)
//      Menaruh data yang sudah ditangkap ke widget yang ditentukan
        header_title.text = titleMovie
        text_overview.text = overviewMovie
        release_year.text = timeSetUp(releaseDateMovie!!)
        votes_average.text = votesaverageMovie.toString()

        Picasso.get()
                .load(EndPoints.IMAGE_URL_BACKDROP + backdropMovie)
                .placeholder(R.drawable.placeholder)
                .into(header_thumbnail)
//      Mengatur bentuk dari recyclerView trailer, yang ada di dalam halaman DetailActivity.kt
        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler_trailer.layoutManager = linearLayoutManager
//      Memanggil dan, menaruh data ke method yang dibutuhkan
        votesCount(votecountMovie)
        initCollapsingToolbar(titleMovie)
        loadDetail(idMovie)
        loadTrailer(idMovie)

        rb_votes.rating = votesaverageMovie.toFloat() / 2

    }

    //     Mengatur format tulisan waktu yang akan ditampilkan dari data yang ditangkap
    private fun timeSetUp(releasedateMovie: String): String {
        val time = Time()
        time.parse3339(releasedateMovie)
        return DateUtils.getRelativeTimeSpanString(time.toMillis(false),
                System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_ALL).toString()

    }

    //     Mengatur tampilan vote yang ada di dalam ratingBar
    private fun votesCount(voteCount: Int) {
        if (voteCount >= 3) {
            votes_result.text = voteCount.toString() + " VOTES"
        } else {
            votes_result.text = voteCount.toString() + " VOTE"
        }
    }

    //    Mengatur scrollingBar
    private fun initCollapsingToolbar(titleMovie: String) {
        toolbar_layout_detail.title = ""
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {

            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = app_bar.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    toolbar_layout_detail.title = titleMovie
                    isShow = true
                } else if (isShow) {
                    toolbar_layout_detail.title = ""
                    isShow = false
                }
            }

        })

    }

    //      Mengambil data untuk detailActivity memakai Retrofit dan GSON
    private fun loadDetail(idMovie: Int) {

        var api = InitRetrofit().getInitInstance()
        var call = api.request_detail(
                idMovie,
                EndPoints.API_KEY
        )
        call.enqueue(object : Callback<DetaiPOKO> {
            override fun onResponse(call: Call<DetaiPOKO>?, response: retrofit2.Response<DetaiPOKO>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        text_durasi.text = response.body()?.runtime + " Minutes"
                        content_original_title.text = response.body()?.original_title
                        content_premiere.text = timeSetUp(releaseDateMovie!!)
                        content_description.text = response.body()?.overview

                        for (i in response.body()?.genres!!.indices) {
                            val genre = response.body()?.genres!![i]

                            if (i < response.body()?.genres!!.size - 1) {
                                content_type.append(genre.genre + ", ")
                            } else {
                                content_type.append(genre.genre)
                            }
                        }

                        for (i in response.body()?.production_companies!!.indices) {
                            var company = response.body()?.production_companies!![i]
                            if (i < response.body()?.production_companies!!.size - 1) {
                                content_production.append(company.companyName + ", ")
                            } else {
                                content_production.append(company.companyName)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<DetaiPOKO>?, t: Throwable?) {

            }

        })

    }

    //      Mengambil data untuk recyclerView Trailer yang ada di dalam DetailActivity.kt memakai Retrofit dan GSON
    private fun loadTrailer(idMovie: Int) {
        progressbar_trailers.visibility = View.GONE

        var api = InitRetrofit().getInitInstance()
        var call = api.request_trailer(idMovie, EndPoints.API_KEY)
        call.enqueue(object : Callback<TrailersPOKO> {

            override fun onFailure(call: Call<TrailersPOKO>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<TrailersPOKO>?, response: retrofit2.Response<TrailersPOKO>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        progressbar_trailers.visibility = View.GONE
                        var data = response.body()?.trailer_results
                        val adapter = TrailersAdapter(this@DetailActivity, data)
                        recycler_trailer.adapter = adapter
                    }
                }
            }

        })
    }
}
