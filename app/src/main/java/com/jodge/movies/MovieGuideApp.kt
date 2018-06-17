package com.jodge.movies

import android.app.Application
import com.bumptech.glide.request.target.ViewTarget
import com.jodge.movies.ui.details.DetailsComponent
import com.jodge.movies.ui.details.DetailsModule
import com.jodge.movies.di.AppComponent
import com.jodge.movies.di.AppModule
import com.jodge.movies.di.DaggerAppComponent
import com.jodge.movies.ui.listing.ListingComponent
import com.jodge.movies.ui.listing.ListingModule
import com.jodge.movies.network.NetworkModule

class MovieGuideApp : Application() {

    private lateinit var appComponent: AppComponent
    private lateinit var listingComponent: ListingComponent
    private lateinit var detailsComponent: DetailsComponent

    override fun onCreate() {
        super.onCreate()
        ViewTarget.setTagId(R.id.glide_tag);
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .appModule(AppModule(this)).build()
    }

    fun createListingComponent(): ListingComponent {
        listingComponent = appComponent.plus(ListingModule())
        return listingComponent
    }
    fun createDetailsComponent(): DetailsComponent {
        detailsComponent = appComponent.plus(DetailsModule())
        return detailsComponent
    }

    fun releaseListingComponent() {
    }
    fun releaseDetailsomponent() {
    }

}