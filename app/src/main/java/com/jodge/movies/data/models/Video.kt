package com.jodge.movies.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.jodge.movies.util.EMPTY
import com.jodge.movies.util.YOUTUBE_THUMBNAIL_URL
import com.jodge.movies.util.YOUTUBE_VIDEO_URL

class Video(
        val id: String,
        val name: String,
        val site: String,
        @SerializedName("key") val videoId: String,
        val size: Int,
        val type: String
) : Parcelable {

    constructor(source: Parcel): this (
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        if (dest == null) return
        with(dest) {
            writeString(id)
            writeString(name)
            writeString(site)
            writeString(videoId)
            writeInt(size)
            writeString(type)
        }

    }

    companion object {

        val SITE_YOUTUBE = "Youtube"
        @JvmField
        val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
            override fun createFromParcel(source: Parcel): Video = Video(source)
            override fun newArray(size: Int): Array<Video?>  = arrayOfNulls(size)
        }

        fun getUrl(video: Video): String {
            return when {
                SITE_YOUTUBE.equals(video.site, true) -> String.format(YOUTUBE_VIDEO_URL, video.videoId)
                else -> EMPTY
            }
        }

        fun getThumbnailUrl(video: Video): String {
            return when {
                SITE_YOUTUBE.equals(video.site, true) -> String.format(YOUTUBE_THUMBNAIL_URL, video.videoId)
                else -> EMPTY
            }
        }
    }
}
