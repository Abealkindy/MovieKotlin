package com.rosinante24.firstinthisversion.POKO

import com.google.gson.annotations.SerializedName

/**
 * Created by Rosinante24 on 26/10/17.
 */
class TrailersPOKO {

    @SerializedName("results")
    var trailer_results: List<TrailersPOKO.Data>? = null

    class Data {
        @SerializedName("id")
        var id: String? = null
        @SerializedName("key")
        var key: String? = null
        @SerializedName("name")
        var name: String? = null
        @SerializedName("type")
        var type: String? = null
        @SerializedName("site")
        var site: String? = null
    }

}