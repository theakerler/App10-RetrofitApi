package com.estradaperez.lab10.model

import com.google.gson.annotations.SerializedName

data class SerieModel(
    @SerializedName("id")
    var id:Int,
    @SerializedName("name")
    var name:String,
    @SerializedName("release_date")
    var release_date:String,
    @SerializedName("rating")
    var rating:Int,
    @SerializedName("category")
    var category:String
)
