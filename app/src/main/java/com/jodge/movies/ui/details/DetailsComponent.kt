package com.jodge.movies.ui.details

import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {
    fun inject(target: DetailsFragment)
}