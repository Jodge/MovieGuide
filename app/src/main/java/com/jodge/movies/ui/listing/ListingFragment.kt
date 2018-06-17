package com.jodge.movies.ui.listing

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import com.jodge.movies.MovieGuideApp
import com.jodge.movies.R
import com.jodge.movies.ui.details.DetailsActivity
import com.jodge.movies.ui.listing.sorting.SortingDialogFragment
import com.jodge.movies.data.models.Movie
import kotlinx.android.synthetic.main.fragment_listing.view.*
import javax.inject.Inject

class ListingFragment : Fragment(), ListingView {

    @Inject
    lateinit var presenter: ListingPresenter
    private lateinit var movieListing: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var progressBar: ProgressBar
    private val movies = ArrayList<Movie>(20)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        (context?.applicationContext as MovieGuideApp).createListingComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root =  inflater.inflate(R.layout.fragment_listing, container, false)
        movieListing = root.movies_listing
        progressBar = root.progress_bar
        initLayoutReferences()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        (context?.applicationContext as MovieGuideApp).releaseListingComponent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroy()

    }

    override fun showMovies(movies: List<Movie>) {
        progressBar.visibility = View.GONE

        this.movies.clear()
        this.movies.addAll(movies)
        movieListing.adapter = adapter
        movieListing.invalidate()
    }

    override fun loadingStarted() {
        progressBar.visibility = View.VISIBLE
    }

    override fun loadingFailed(errorMessage: String) {
        progressBar.visibility = View.GONE
        val snackBar = Snackbar.make(movieListing, errorMessage, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction("TAP TO RETRY", {
            presenter.setView(this)
        })
        snackBar.show()
    }

    private fun initLayoutReferences() {
        movieListing.setHasFixedSize(true)

        val columns: Int
        when (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            true -> columns = 3
            false -> columns = resources.getInteger(R.integer.no_of_columns)
        }
        val layoutManager = GridLayoutManager(context, columns)
        movieListing.layoutManager = layoutManager
        movieListing.setHasFixedSize(true)
        adapter = ListingAdapter(movies) {
            val intent = DetailsActivity.newIntent(context!!, it)
            startActivity(intent)
        }
        movieListing.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId) {
            R.id.action_sort -> { displaySortingOption() }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun displaySortingOption() {
        val sortingDialogFragment = SortingDialogFragment.newInstance(presenter)
        sortingDialogFragment.show(fragmentManager, "Select Quantity")
    }
}