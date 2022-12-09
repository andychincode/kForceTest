package com.kforce.test.model

import com.google.gson.annotations.SerializedName

data class Longform(
    @SerializedName("lf")
    var longform: String?,
    @SerializedName("freq")
    var freq: Int,
    @SerializedName("since")
    var since: Int,
    @SerializedName("vars")
    var vars: List<Any>
)
