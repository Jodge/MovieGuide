package com.jodge.movies.ui.listing.sorting

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import com.jodge.movies.MovieGuideApp
import com.jodge.movies.R
import com.jodge.movies.ui.listing.ListingPresenter
import kotlinx.android.synthetic.main.sorting_options.view.*
import javax.inject.Inject

class SortingDialogFragment : DialogFragment(), SortingDialogView, RadioGroup.OnCheckedChangeListener {

    @Inject
    lateinit var sortingDialogPresenter: SortingDialogPresenter

    private lateinit var mostPopular: RadioButton
    private lateinit var highestRated: RadioButton
    private lateinit var favorites: RadioButton
    private lateinit var sortingOptionsGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        (activity?.application as MovieGuideApp).createListingComponent().inject(this)
        sortingDialogPresenter.setView(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflater.inflate(R.layout.sorting_options, null)
        mostPopular = dialogView.most_popular
        highestRated = dialogView.highest_rated
        favorites = dialogView.favorites
        sortingOptionsGroup = dialogView.sorting_group
        initViews()

        val dialog = Dialog(activity)
        dialog.setContentView(dialogView)
        dialog.setTitle(R.string.sort_by)
        dialog.show()
        return dialog
    }

    override fun setPopularChecked() {
        mostPopular.isChecked=true
    }

    override fun setHighestRatedChecked() {
        highestRated.isChecked=true
    }

    override fun setFavoritesChecked() {
        favorites.isChecked=true
    }

    override fun dismissDialog() {
        dismiss()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

        when(checkedId) {
            R.id.most_popular -> {
                sortingDialogPresenter.onPopularMoviesSelected()
                listingPresenter.displayMovies()
            }
            R.id.highest_rated -> {
                sortingDialogPresenter.onHighestRatedMoviesSelected()
                listingPresenter.displayMovies()
            }
            R.id.favorites -> {
                sortingDialogPresenter.onFavoritesSelected()
                listingPresenter.displayMovies()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sortingDialogPresenter.destroy()
    }

    private fun initViews() {
        sortingDialogPresenter.setLastSavedOption()
        sortingOptionsGroup.setOnCheckedChangeListener(this)
    }

    companion object {
        private lateinit var listingPresenter: ListingPresenter
        fun newInstance(listingPresenter: ListingPresenter): SortingDialogFragment {
            this.listingPresenter = listingPresenter
            return SortingDialogFragment()
        }
    }
}
