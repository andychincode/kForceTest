package com.kforce.test.model

import com.google.gson.annotations.SerializedName

data class AcronymData (
    @SerializedName("sf")
    var shortform: String?,
    @SerializedName("lfs")
    var longforms: List<Longform>
)