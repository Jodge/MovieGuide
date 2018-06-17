package com.jodge.movies.ui.listing

import com.jodge.movies.ui.listing.sorting.SortingDialogFragment
import com.jodge.movies.ui.listing.sorting.SortingModule
import dagger.Subcomponent

@ListingScope
@Subcomponent(modules = [ListingModule::class, SortingModule::class])
interface ListingComponent {

    fun inject(target: ListingFragment)

    fun inject(target: SortingDialogFragment)
}
