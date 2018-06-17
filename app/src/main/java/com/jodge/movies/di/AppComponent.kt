package com.jodge.movies.di

import com.jodge.movies.api.ApiModule
import com.jodge.movies.ui.details.DetailsComponent
import com.jodge.movies.ui.details.DetailsModule
import com.jodge.movies.ui.favorites.FavoritesModule
import com.jodge.movies.ui.listing.ListingComponent
import com.jodge.movies.ui.listing.ListingModule
import com.jodge.movies.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,  RoomModule::class, NetworkModule::class, ApiModule::class, FavoritesModule::class])
interface AppComponent {

    fun plus(listingModule: ListingModule): ListingComponent

    fun plus(detailsModule: DetailsModule) : DetailsComponent
}