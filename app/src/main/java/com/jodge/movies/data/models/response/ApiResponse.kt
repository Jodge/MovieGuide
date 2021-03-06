package com.jodge.movies.data.models.response

import com.google.gson.annotations.SerializedName

class ApiResponse<T : Any> {

    @SerializedName("results")
    lateinit var data: List<T>
}
