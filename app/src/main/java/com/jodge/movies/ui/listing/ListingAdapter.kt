package com.jodge.movies.ui.listing

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.jodge.movies.R
import com.jodge.movies.data.models.Movie
import kotlinx.android.synthetic.main.movie_grid_item.view.*
import org.jetbrains.annotations.NotNull

class ListingAdapter(private var movies: List<Movie> = arrayListOf(), private val itemClick: (Movie) -> Unit) : RecyclerView.Adapter<ListingAdapter.ViewHolder>() {

    private lateinit var movieTitle: TextView
    private lateinit var moviePoster: ImageView


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(movies[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent?.context)).inflate(R.layout.movie_grid_item, parent, false)
        movieTitle = root.title
        moviePoster = root.poster
        return ViewHolder(root, itemClick)
    }

    inner class ViewHolder(@NotNull root: View, private val itemClick: (Movie) -> Unit) : RecyclerView.ViewHolder(root) {

        val titleBackground: View = root.title_background
        private val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)


        fun bindMovie(movie: Movie) {
            with(movie) {
                movieTitle.text = title
                Glide.with(itemView.context)
                        .asBitmap()
                        .load(movie.getPosterUrl())
                        .apply(options)
                        .into(object : BitmapImageViewTarget(moviePoster) {
                            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                                super.onResourceReady(bitmap, transition)
                                Palette.from(bitmap).generate { p -> setBackgroundColor(p, titleBackground, itemView.context) }
                            }
                        })
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun setBackgroundColor(palette: Palette, titleBackground: View, context: Context) {
        titleBackground.setBackgroundColor(palette.getVibrantColor(context.resources.getColor(R.color.black_translucent_60)))
    }
}
