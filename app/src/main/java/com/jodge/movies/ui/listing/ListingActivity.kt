package com.jodge.movies.ui.listing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import com.jodge.movies.R
import com.jodge.movies.util.ActivityUtils

class ListingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        setToolbar()
        loadListingFragment(savedInstanceState)
    }

    private fun setToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(R.string.movie_guide)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
    }

    private fun loadListingFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            ActivityUtils.addFragmentToActivity(supportFragmentManager, ListingFragment(),
                    R.id.listingContainer, ListingFragment::class.simpleName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}