package com.jodge.movies.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jodge.movies.R
import com.jodge.movies.data.models.Movie
import com.jodge.movies.util.ActivityUtils

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val movie = intent.getParcelableExtra(MOVIE) as Movie

        if (savedInstanceState == null) {
            ActivityUtils.addFragmentToActivity(supportFragmentManager, DetailsFragment.newInstance(movie),
                    R.id.details_container, DetailsFragment::class.simpleName)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> { NavUtils.navigateUpFromSameTask(this)}
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private  val MOVIE = "com.jodge.movies.ui.details.MOVIE"
        fun newIntent(packageContext: Context, movie: Movie) : Intent {
            val intent = Intent(packageContext, DetailsActivity::class.java)
            intent.putExtra(MOVIE, movie)
            return intent
        }
    }
}
