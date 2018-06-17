package com.jodge.movies.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.jodge.movies.util.Constants

class Movie(
        @SerializedName("id") val id: String,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("title") val title: String,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("backdrop_path") val backdropPath: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("release_date") val releaseDate: String) : Parcelable {

    constructor(source: Parcel): this (
            source.readString(),
            source.readDouble(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeDouble(voteAverage)
        dest?.writeString(title)
        dest?.writeString(posterPath)
        dest?.writeString(backdropPath)
        dest?.writeString(overview)
        dest?.writeString(releaseDate)
    }

    fun getPosterUrl(): String {
        return "${Constants.BASE_POSTER_PATH}$posterPath"
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?>  = arrayOfNulls(size)
        }
    }

}
