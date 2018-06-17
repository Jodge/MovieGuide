package com.jodge.movies.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jodge.movies.MovieGuideApp
import com.jodge.movies.R
import com.jodge.movies.util.Constants
import com.jodge.movies.data.models.Movie
import com.jodge.movies.data.models.Review
import com.jodge.movies.data.models.Video
import kotlinx.android.synthetic.main.fragment_details.view.*
import kotlinx.android.synthetic.main.trailers_and_reviews.view.*
import javax.inject.Inject

class DetailsFragment : Fragment(), DetailsView, View.OnClickListener {

    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var rating: TextView
    private lateinit var overview: TextView
    private lateinit var label: TextView
    private lateinit var trailers: LinearLayout
    private lateinit var horizontalScrollView: HorizontalScrollView
    private lateinit var reviews: TextView
    private lateinit var reviewContainer: LinearLayout
    private lateinit var favorite: FloatingActionButton
    private var toolbar: Toolbar? = null

    @Inject
    lateinit var presenter: DetailsPresenter

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable(Constants.MOVIE) as Movie
        retainInstance = true
        (context?.applicationContext as MovieGuideApp).createDetailsComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        collapsingToolbar = root.collapsing_toolbar
        toolbar = root.toolbar
        poster = root.movie_poster
        title = root.movie_name
        releaseDate = root.movie_year
        rating = root.movie_rating
        overview = root.movie_description
        reviews = root.reviews_label
        reviewContainer = root.reviews
        label = root.trailers_label
        trailers = root.trailers
        horizontalScrollView = root.trailers_container
        favorite = root.favorite
        setToolbar()

        favorite.setOnClickListener { onFavoriteClick() }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        presenter.showDetails(movie)
        presenter.showFavoriteButton(movie);
    }

    override fun onDestroy() {
        super.onDestroy()
        (context?.applicationContext as MovieGuideApp).releaseDetailsomponent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroy()
    }

    override fun showDetails(movie: Movie) {
        Glide.with(context).load("${Constants.BASE_BACKDROP_PATH}${movie.backdropPath}").into(poster)
        title.text = movie.title
        releaseDate.text = getString(R.string.release_date, movie.releaseDate)
        overview.text = movie.overview
        presenter.showTrailers(movie)
        presenter.showReviews(movie)
    }

    override fun showTrailers(trailers: List<Video>) {
        if (trailers.isEmpty()) {
            label.visibility = View.GONE
            this.trailers.visibility = View.GONE
            horizontalScrollView.visibility = View.GONE
        } else {
            label.visibility = View.VISIBLE
            this.trailers.visibility = View.VISIBLE
            horizontalScrollView.visibility = View.VISIBLE

            this.trailers.removeAllViews()
            val inflater = activity?.layoutInflater
            val options = RequestOptions().placeholder(R.color.colorPrimary)
                    .centerCrop()
                    .override(150, 150)

            for (trailer in trailers) {
                val thumbContainer = inflater!!.inflate(R.layout.video, this.trailers, false)
                val thumbView = thumbContainer.findViewById<ImageView>(R.id.video_thumb)
                thumbView.tag = Video.getUrl(trailer)
                thumbView.requestLayout()
                thumbView.setOnClickListener(this)
                Glide.with(context).load(Video.getThumbnailUrl(trailer))
                        .apply(options)
                        .into(thumbView)

                this.trailers.addView(thumbContainer)
            }

        }
    }

    override fun showReviews(reviews: List<Review>) {
        if (reviews.isEmpty()) {
            this.reviews.visibility = View.GONE
            reviewContainer.visibility = View.GONE
        } else {
            this.reviews.visibility = View.VISIBLE
            reviewContainer.visibility = View.VISIBLE

            reviewContainer.removeAllViews()
            val inflater = activity?.layoutInflater
            for (review in reviews) {
                val reviewsContainer: ViewGroup = inflater?.inflate(R.layout.review, reviewContainer, false) as ViewGroup
                val reviewAuthor = reviewsContainer.findViewById<TextView>(R.id.review_author)
                val reviewContent = reviewsContainer.findViewById<TextView>(R.id.review_content)
                reviewAuthor.text = review.author
                reviewContent.text = review.content
                reviewContent.setOnClickListener(this)
                reviewContainer.addView(reviewsContainer)
            }

        }
    }

    override fun showFavorited() {
        favorite.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_white_24dp))
    }

    override fun showUnFavorited() {
        favorite.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_favorite_border_white_24dp))
    }

    private fun setToolbar() {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        collapsingToolbar.title = getString(R.string.movie_details)
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar)
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar)
        collapsingToolbar.isTitleEnabled = true

        if (toolbar != null) {
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            val actionBar = (activity as AppCompatActivity).supportActionBar
            actionBar!!.setDisplayHomeAsUpEnabled(true)
        } else {
            // Don't inflate. Tablet is in landscape mode
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.video_thumb -> { onThumbnailClick(v) }
            R.id.review_content -> { onReviewClick(v as TextView)}
        }
    }

    private fun onThumbnailClick(view: View) {
        val videoUrl = view.tag as String
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(playVideoIntent)
    }

    private fun onReviewClick(view: TextView) {
        if (view.maxLines == 5) view.maxLines = 500
        else view.maxLines = 5
    }

    private fun onFavoriteClick() {
        presenter.onFavoriteClick(movie)
    }

    companion object {
        fun newInstance(movie: Movie): DetailsFragment {
            val args = Bundle()
            args.putParcelable(Constants.MOVIE, movie)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
