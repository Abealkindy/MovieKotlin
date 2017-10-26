package com.rosinante24.firstinthisversion.POKO

import com.google.gson.annotations.SerializedName

/**
 * Created by Rosinante24 on 26/10/17.
 */
class DetaiPOKO {

    @SerializedName("backdrop_path")
    val backdrop_path: String? = null

    @SerializedName("budget")
    val budget: String? = null

    @SerializedName("genres")
    val genres: List<Genre>? = null

    class Genre {

        @SerializedName("id")
        val genreId: String? = null

        @SerializedName("name")
        val genre: String? = null

    }

    @SerializedName("homepage")
    val homepage_url: String? = null

    @SerializedName("original_title")
    val original_title: String? = null

    @SerializedName("overview")
    val overview: String? = null

    @SerializedName("production_companies")
    val production_companies: List<Company>? = null

    class Company {

        @SerializedName("name")
        val companyName: String? = null

        @SerializedName("id")
        val companyId: String? = null

    }

    @SerializedName("release_date")
    val release_date: String? = null

    @SerializedName("runtime")
    val runtime: String? = null

}